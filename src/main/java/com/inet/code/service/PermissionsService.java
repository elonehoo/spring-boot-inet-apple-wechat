package com.inet.code.service;

import com.inet.code.entity.po.Permissions;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author HCY
 * @since 2021-03-04
 */
public interface PermissionsService extends IService<Permissions> {

    /**
     * 获取权限名称的序号
     * @author HCY
     * @since 2021/3/5 下午3:27
     * @param permissionsName: 权限名称
     * @return java.lang.String
    */
    String getByName(String permissionsName);
}
