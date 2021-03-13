package com.inet.code.mapper;

import com.inet.code.entity.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.inet.code.entity.vo.AllUserInfo;
import com.inet.code.entity.vo.PermissionsInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author HCY
 * @since 2021-03-04
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过用户序号查找用户的全部信息
     * @author HCY
     * @since 2021/3/5 下午12:23
     * @param userId: 序号
     * @return com.inet.code.entity.vo.AllUserInfo
    */
    AllUserInfo getByUserId(String userId);

    /**
     * 通过用户序号获取权限集合
     * @author HCY
     * @since 2021/3/5 下午12:42
     * @param userId: 用户序号
     * @return java.util.List<com.inet.code.entity.vo.PermissionsInfo>
    */
    List<PermissionsInfo> getPermissionsList(String userId);

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
    Integer getIsNumber(String number);

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
