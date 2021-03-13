package com.inet.code.service;

import com.inet.code.entity.po.RoleUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户角色 服务类
 * </p>
 *
 * @author HCY
 * @since 2021-03-04
 */
public interface RoleUserService extends IService<RoleUser> {

    /**
     * 通过用户的学号查找用户的权限
     * @author HCY
     * @since 2021/3/12 下午9:37
     * @param userId: 用户序号
     * @return com.inet.code.entity.po.RoleUser
    */
    RoleUser getByUserId(String userId);
}
