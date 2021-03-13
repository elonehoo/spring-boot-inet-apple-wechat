package com.inet.code.custom.impl;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.inet.code.custom.BaseCustom;
import com.inet.code.entity.dto.Amend;
import com.inet.code.entity.dto.Login;
import com.inet.code.entity.dto.Register;
import com.inet.code.entity.po.*;
import com.inet.code.entity.vo.AllUserInfo;
import com.inet.code.entity.vo.CheckIng;
import com.inet.code.result.Result;
import com.inet.code.service.*;
import com.inet.code.utils.BeanUtil;
import com.inet.code.utils.FileUtils;
import com.inet.code.utils.JwtUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 通用接口实现类
 * @author HCY
 * @since 2021/3/5 下午2:11
*/
@Service
public class BaseCustomImpl implements BaseCustom {

    @Resource
    private UserService userService;

    @Resource
    private CipherService cipherService;

    @Resource
    private RoleService roleService;

    @Resource
    private RoleUserService roleUserService;

    @Resource
    private PermissionsService permissionsService;

    @Resource
    private PermissionsUserService permissionsUserService;

    @Resource
    private CheckService checkService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 新用户注册
     * @author HCY
     * @since 2021/3/5 下午2:42
     * @param register: 注册的实体类
     * @param path: URL路径
     * @return com.inet.code.result.Result
     */
    @Override
    public Result register(Register register, String path) {
        //判断名字是否为空
        if (register.getName().isBlank()) {
            return new Result().result500("注册姓名为空",path);
        }
        //判断学号
        if (register.getNumber().isBlank() && register.getNumber().length() == 10){
            return new Result().result500("学号不正确",path);
        }
        //判断学号是否重复
        if (userService.getIsNumber(register.getNumber())) {
            return new Result().result500("学号重复了",path);
        }
        //判断电话号码
        if (! Validator.isMobile(register.getTelephone())) {
            return new Result().result500("电话号码不正确",path);
        }
        User user = BeanUtil.copy(register, User.class);
        //存储用户基本信息
        if (userService.save(user)) {
            //获取用户的主键
            String userId = userService.getByNumber(register.getNumber());
            //设置密码实体类
            Cipher cipher = new Cipher(userId, SecureUtil.md5(register.getCipher()));
            //存储密码
            if (cipherService.save(cipher)) {
                //存储角色
                String roleId = roleService.getByName("ordinaryMembers");
                if (roleUserService.save(new RoleUser(userId,roleId))) {
                    //存储权限
                    List<PermissionsUser> list = new ArrayList<>();
                    List<String> permissionsNames = new ArrayList<>();
                    permissionsNames.add("signIn");
                    permissionsNames.add("signOut");
                    permissionsNames.add("checkingProgress");
                    permissionsNames.add("modifyUserInformation");
                    permissionsNames.add("quit");
                    permissionsNames.add("landing");
                    for (String permissionsName : permissionsNames){
                        list.add(new PermissionsUser(
                                 userId
                                ,permissionsService.getByName(permissionsName)
                        ));
                    }
                    if (permissionsUserService.saveBatch(list)) {
                        //存储签到模块
                        if (checkService.save(new Check(userId, 0,0L))) {
                            return new Result().result200("注册成功",path);
                        }
                    }
                }
            }else {
                if (userService.removeById(userId)) {
                    return new Result().result500("注册失败",path);
                }
            }
        }else {
            return new Result().result500("注册失败",path);
        }
        return new Result().result500("注册失败",path);
    }

    /**
     * 登陆的操作
     * @author HCY
     * @since 2021/3/5 下午9:41
     * @param login: 登陆的实体类
     * @param path: URL路径
     * @return com.inet.code.result.Result
     */
    @Override
    public Result login(Login login, String path) {
        //获取登陆操作
        String userId = userService.getLogin(
                login.getAccount()
                ,SecureUtil.md5(login.getPassword()));
        //判断账号密码是否正确
        if (StrUtil.isBlank(userId)){
            return new Result().result404("未找到用户",path);
        }
        //通过账号和密码获取的用户信息
        AllUserInfo user = userService.getByAllUserInfo(userId);
        //设置token内含数据
        Map<String, String> map = new HashMap<>(2);
        map.put("userId",user.getId());
        map.put("userName",user.getName());
        //设置token
        String token = JwtUtils.getToken(map);
        //存储（token，用户数据）
        redisTemplate.opsForValue().set(token,user,7, TimeUnit.DAYS);
        //设置返回值
        Map<String, Object> results = new HashMap<>(2);
        results.put("info","登录成功");
        results.put("token",token);
        return new Result().result200(results,path);
    }

    /**
     * 退出请求
     *  清除token
     * @author HCY
     * @since 2021/3/6 上午9:33
     * @param token: 令牌
     * @param path: URL路径
     * @return com.inet.code.result.Result
     */
    @Override
    public Result logOut(String token, String path) {
        //判断token是否存在
        if (redisTemplate.opsForValue().get(token) == null){
            return new Result().result404("退出产生异常，联系管理员",path);
        }
        //清空token
        if (redisTemplate.delete(token)) {
            return new Result().result200("退出成功",path);
        }
        return new Result().result500("退出操作产生异常",path);
    }

    /**
     * 签到，签退操作
     * @author HCY
     * @since 2021/3/6 上午10:07
     * @param token: 令牌
     * @param path: URL路径
     * @return com.inet.code.result.Result
     */
    @Override
    public Result signing(String token, String path) {
        //通过token获取用户序号
        AllUserInfo user = (AllUserInfo) redisTemplate.opsForValue().get(token);
        //判断user是否存在
        if (user == null){
            return new Result().result404("未您的找到登陆用户，请进行登陆操作",path);
        }
        //判断进行的是签到操作还是签退操作
        //0 未签到
        //1 签到未同意
        //2 签到
        //获取状态
        Integer state = checkService.getJudgeStateByUserId(user.getId());
        String finish = "产生问题，请联系管理员";
        if (state == 0) {
            //签到
            finish = checkService.getSigning(user.getId());
        }else if (state == 2){
            //签退
            finish = checkService.getSignOut(user.getId());
        }else if (state == 1){
            //签到未同意
            finish = "签退失败，管理员尚未给您同意签到";
        }
        return new Result().result200(finish,path);
    }

    /**
     * 修改头像
     * @author HCY
     * @since 2021/3/6 下午11:29
     * @param token: token
     * @param file: 文件
     * @param path: URL路径
     * @return com.inet.code.result.Result
     */
    @Override
    public Result getAmendPicture(String token,MultipartFile file, String path) {
        //获取用户的数据
        AllUserInfo user = (AllUserInfo) redisTemplate.opsForValue().get(token);
        //获取文件上传的URL路径
        Result uploading = FileUtils.getUploading(file, path);
        if (uploading.getStatus() != 200) {
            return new Result().result401("头像修改失败",path);
        }else {
            //获取url
            String url = (String) uploading.getMessage();
            //修改用户的URL
            user.setPortrait(url);
            //替换redis
            if (redisTemplate.delete(token)) {
                redisTemplate.opsForValue().set(token,user,7, TimeUnit.DAYS);
            }
            //替换数据库
            User copy = BeanUtil.copy(user, User.class);
            if (userService.updateById(copy)) {
                return new Result().result200("头像修改成功",path);
            }
        }
        return new Result().result404("头像修改失败",path);
    }

    /**
     * 修改密码
     * @author HCY
     * @since 2021/3/7 下午5:47
     * @param token: 令牌
     * @param amend: 修改信息
     * @param path: URL路径
     * @return com.inet.code.result.Result
     */
    @Override
    public Result getAmendCipher(String token, Amend amend, String path) {
        //获取密码
        Cipher cipher = cipherService.getByUserId(JwtUtils.getString(token, "userId"));
        //获取旧密码
        String oldPassword = SecureUtil.md5(amend.getOldPassword());
        //判断旧密码是否正确
        if (!oldPassword.equals(cipher.getCipher())) {
            return new Result().result401("旧密码输入错误",path);
        }
        //判断两次新密码是否一致
        if (!amend.getNewOnePassword().equals(amend.getNewTwoPassword())) {
            return new Result().result401("两次输入的新密码不一致，请重新输入",path);
        }
        //进行密码的修改
        cipher.setCipher(SecureUtil.md5(amend.getNewOnePassword()));
        //进行修改
        if (cipherService.updateById(cipher)) {
            return new Result().result200("密码修改成功",path);
        }
        return new Result().result200("密码修改失败",path);
    }

    /**
     * 分页查看正在签到的用户
     * @author HCY
     * @since 2021/3/8 上午9:06
     * @param token: 令牌
     * @param current: 页数
     * @param size: 条目书
     * @param path: URL路径
     * @return com.inet.code.result.Result
     */
    @Override
    public Result pageIng(String token, Integer current, Integer size, String path) {
        IPage<CheckIng> page = checkService.checkingProgress(new Page<CheckIng>(current,size));
        return new Result().result200(page,path);
    }

    /**
     * 上传文件
     * @author HCY
     * @since 2021/3/9 上午9:36
     * @param file: 文件
     * @param path: URL路径
     * @return com.inet.code.result.Result
     */
    @Override
    public Result getUpload(MultipartFile file, String path) {
        return FileUtils.getUploading(file,path);
    }

    /**
     * 通过token获取用户的数据
     * @author HCY
     * @since 2021/3/9 下午3:23
     * @param token: 令牌
     * @param path: URL路径
     * @return com.inet.code.result.Result
     */
    @Override
    public Result getFindUser(String token, String path) {
        //通过token获取用户序号
        String userId = JwtUtils.getString(token, "userId");
        User user = userService.getById(userId);
        Map<String, Object> map = new HashMap<>();
        map.put("headPortrait",user.getPortrait());
        map.put("nickName",user.getName());
        return new Result().result200(map,path);
    }

    /**
     * 获取修改数据
     * @author HCY
     * @since 2021/3/9 下午5:47
     * @param token: 令牌
     * @param path: URL路径
     * @return com.inet.code.result.Result
     */
    @Override
    public Result getAmendInfo(String token, String path) {
        String userId = JwtUtils.getString(token, "userId");
        //通过userId获取用户的实体信息
        User user = userService.getById(userId);
        return new Result().result200(user.getPortrait(),path);
    }

    /**
     * 获取签到的具体数据
     * @author HCY
     * @since 2021/3/9 下午8:35
     * @param token: 令牌
     * @param path: URL路径
     * @return com.inet.code.result.Result
     */
    @Override
    public Result getSignInfo(String token, String path) {
        //设置用户的序号
        String userId = JwtUtils.getString(token, "userId");
        //通过用户序号获取到签到的数据
        Check check = checkService.getByUserId(userId);
        //设置返回值
        Map<String, Object> map = new HashMap<>(2);
        map.put("signState",check.getCheckState());
        map.put("signTime",check.getCheckTotal());
        return new Result().result200(map,path);
    }
}
