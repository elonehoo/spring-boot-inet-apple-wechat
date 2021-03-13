package com.inet.code.service.impl;

import com.inet.code.entity.po.RoleUser;
import com.inet.code.mapper.RoleUserMapper;
import com.inet.code.service.RoleUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户角色 服务实现类
 * </p>
 *
 * @author HCY
 * @since 2021-03-04
 */
@Service
public class RoleUserServiceImpl extends ServiceImpl<RoleUserMapper, RoleUser> implements RoleUserService {

    @Resource
    private RoleUserMapper roleUserMapper;

    /**
     * 通过用户的学号查找用户的权限
     * @author HCY
     * @since 2021/3/12 下午9:37
     * @param userId: 用户序号
     * @return com.inet.code.entity.po.RoleUser
     */
    @Override
    public RoleUser getByUserId(String userId) {
        return roleUserMapper.selectByUserId(userId);
    }
}
