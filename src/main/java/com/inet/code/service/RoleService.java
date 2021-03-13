package com.inet.code.service;

import com.inet.code.entity.po.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author HCY
 * @since 2021-03-04
 */
public interface RoleService extends IService<Role> {

    /**
     * 通过权限名称获取到权限的序号
     * @author HCY
     * @since 2021/3/5 下午3:07
     * @param roleName:权限名称
     * @return java.lang.String
    */
    String getByName(String roleName);
}
