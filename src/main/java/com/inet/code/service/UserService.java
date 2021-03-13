package com.inet.code.service;

import com.inet.code.entity.po.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.inet.code.entity.vo.AllUserInfo;
import com.inet.code.mapper.UserMapper;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author HCY
 * @since 2021-03-04
 */
public interface UserService extends IService<User> {

    /**
     * 获取用户的全部数据
     * @author HCY
     * @since 2021/3/5 上午10:00
     * @param userId: 用户主键
     * @return com.inet.code.entity.vo.AllUserInfo
    */
    AllUserInfo getByAllUserInfo(String userId);

    /**
     * 通过学号查询用户的主键
     * @author HCY
     * @since 2021/3/5 下午2:54
     * @param number: 学号
     * @return java.lang.String
    */
    String getByNumber(String number);

    /**
     * 通过学号判断是否重复
     * @author HCY
     * @since 2021/3/5 下午8:02
     * @param number: 学号
     * @return java.lang.Boolean
    */
    Boolean getIsNumber(String number);

    /**
     * 登陆操作
     * @author HCY
     * @since 2021/3/5 下午10:29
     * @param account: 账号
     * @param password: 密码
     * @return java.lang.String
    */
    String getLogin(String account, String password);
}
