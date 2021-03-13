package com.inet.code.mapper;

import com.inet.code.entity.po.Permissions;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author HCY
 * @since 2021-03-04
 */
@Mapper
public interface PermissionsMapper extends BaseMapper<Permissions> {

    /**
     * 获取权限名称的序号
     * @author HCY
     * @since 2021/3/5 下午3:27
     * @param permissionsName: 权限名称
     * @return java.lang.String
     */
    String getByName(String permissionsName);
}
