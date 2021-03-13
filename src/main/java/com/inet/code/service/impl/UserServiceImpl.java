package com.inet.code.service.impl;

import com.inet.code.entity.po.User;
import com.inet.code.entity.vo.AllUserInfo;
import com.inet.code.mapper.UserMapper;
import com.inet.code.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author HCY
 * @since 2021-03-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 获取用户的全部数据
     * @author HCY
     * @since 2021/3/5 上午10:00
     * @param userId: 用户主键
     * @return com.inet.code.entity.vo.AllUserInfo
    */
    @Override
    public AllUserInfo getByAllUserInfo(String userId) {
        return userMapper
                .getByUserId(userId)
                .setPermissions(userMapper.getPermissionsList(userId));
    }

    /**
     * 通过学号查询用户的主键
     * @author HCY
     * @since 2021/3/5 下午2:54
     * @param number: 学号
     * @return java.lang.String
     */
    @Override
    public String getByNumber(String number) {
        return userMapper.getByNumber(number);
    }

    /**
     * 通过学号判断是否重复
     * @author HCY
     * @since 2021/3/5 下午8:02
     * @param number: 学号
     * @return java.lang.Boolean
     */
    @Override
    public Boolean getIsNumber(String number) {
        return userMapper.getIsNumber(number) == 1;
    }

    /**
     * 登陆操作
     * @author HCY
     * @since 2021/3/5 下午10:29
     * @param account: 账号
     * @param password: 密码
     * @return java.lang.String
     */
    @Override
    public String getLogin(String account, String password) {
        return userMapper.getLogin(account,password);
    }
}
