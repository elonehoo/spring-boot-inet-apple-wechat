package com.inet.code.mapper;

import com.inet.code.entity.po.RoleUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户角色 Mapper 接口
 * </p>
 *
 * @author HCY
 * @since 2021-03-04
 */
@Mapper
public interface RoleUserMapper extends BaseMapper<RoleUser> {

    /**
     * 通过用户的学号查找用户的权限
     * @author HCY
     * @since 2021/3/12 下午9:37
     * @param userId: 用户序号
     * @return com.inet.code.entity.po.RoleUser
     */
    RoleUser selectByUserId(String userId);
}
