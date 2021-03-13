package com.inet.code.mapper;

import com.inet.code.entity.po.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author HCY
 * @since 2021-03-04
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 通过权限名称获取到权限的序号
     * @author HCY
     * @since 2021/3/5 下午3:07
     * @param roleName:权限名称
     * @return java.lang.String
     */
    String getByName(String roleName);
}
