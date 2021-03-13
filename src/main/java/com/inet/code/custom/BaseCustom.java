package com.inet.code.custom;

import com.inet.code.entity.dto.Amend;
import com.inet.code.entity.dto.Login;
import com.inet.code.entity.dto.Register;
import com.inet.code.result.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * 通用的接口
 * @author HCY
 * @since 2021/3/5 下午2:10
*/
public interface BaseCustom {

    /**
     * 新用户注册
     * @author HCY
     * @since 2021/3/5 下午2:42
     * @param register: 注册的实体类
     * @param path: URL路径
     * @return com.inet.code.result.Result
    */
    Result register(Register register, String path);

    /**
     * 登陆的操作
     * @author HCY
     * @since 2021/3/5 下午9:41
     * @param login: 登陆的实体类
     * @param path: URL路径
     * @return com.inet.code.result.Result
    */
    Result login(Login login, String path);

    /**
     * 退出请求
     * @author HCY
     * @since 2021/3/6 上午9:33
     * @param token: 令牌
     * @param path: URL路径
     * @return com.inet.code.result.Result
    */
    Result logOut(String token, String path);

    /**
     * 签到，签退操作
     * @author HCY
     * @since 2021/3/6 上午10:07
     * @param token: 令牌
     * @param path: URL路径
     * @return com.inet.code.result.Result
    */
    Result signing(String token, String path);

    /**
     * 修改头像
     * @author HCY
     * @since 2021/3/6 下午11:29
     * @param token: 令牌
     * @param file: 文件
     * @param path: URL路径
     * @return com.inet.code.result.Result
    */
    Result getAmendPicture(String token,MultipartFile file, String path);

    /**
     * 修改密码
     * @author HCY
     * @since 2021/3/7 下午5:47
     * @param token: 令牌
     * @param amend: 修改信息
     * @param path: URL路径
     * @return com.inet.code.result.Result
    */
    Result getAmendCipher(String token, Amend amend, String path);

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
    Result pageIng(String token, Integer current, Integer size, String path);

    /**
     * 上传文件
     * @author HCY
     * @since 2021/3/9 上午9:36
     * @param file: 文件
     * @param path: URL路径
     * @return com.inet.code.result.Result
    */
    Result getUpload(MultipartFile file, String path);

    /**
     * 通过token获取用户的数据
     * @author HCY
     * @since 2021/3/9 下午3:23
     * @param token: 令牌
     * @param path: URL路径
     * @return com.inet.code.result.Result
    */
    Result getFindUser(String token, String path);

    /**
     * 获取修改数据
     * @author HCY
     * @since 2021/3/9 下午5:47
     * @param token: 令牌
     * @param path: URL路径
     * @return com.inet.code.result.Result
    */
    Result getAmendInfo(String token, String path);

    /**
     * 获取签到的具体数据
     * @author HCY
     * @since 2021/3/9 下午8:35
     * @param token: 令牌
     * @param path: URL路径
     * @return com.inet.code.result.Result
    */
    Result getSignInfo(String token, String path);
}
