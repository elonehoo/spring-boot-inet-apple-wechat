package com.inet.code.service.impl;

import com.inet.code.entity.po.Role;
import com.inet.code.mapper.RoleMapper;
import com.inet.code.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author HCY
 * @since 2021-03-04
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    /**
     * 通过权限名称获取到权限的序号
     * @author HCY
     * @since 2021/3/5 下午3:07
     * @param roleName:权限名称
     * @return java.lang.String
     */
    @Override
    public String getByName(String roleName) {
        return roleMapper.getByName(roleName);
    }
}
