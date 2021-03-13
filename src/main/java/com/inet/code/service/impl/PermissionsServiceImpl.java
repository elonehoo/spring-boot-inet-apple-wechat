package com.inet.code.service.impl;

import com.inet.code.entity.po.Permissions;
import com.inet.code.mapper.PermissionsMapper;
import com.inet.code.service.PermissionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author HCY
 * @since 2021-03-04
 */
@Service
public class PermissionsServiceImpl extends ServiceImpl<PermissionsMapper, Permissions> implements PermissionsService {

    @Resource
    private PermissionsMapper permissionsMapper;

    /**
     * 获取权限名称的序号
     * @author HCY
     * @since 2021/3/5 下午3:27
     * @param permissionsName: 权限名称
     * @return java.lang.String
     */
    @Override
    public String getByName(String permissionsName) {
        return permissionsMapper.getByName(permissionsName);
    }
}
