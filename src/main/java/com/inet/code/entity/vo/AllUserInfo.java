package com.inet.code.entity.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 全部的用户信息
 * @author HCY
 * @since 2021/3/5 上午9:45
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@ApiModel("全部的用户信息")
public class AllUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户主键
     */
    @ApiModelProperty("用户主键")
    private String id;

    /**
     * 用户学号
     */
    @ApiModelProperty("用户学号")
    private String number;

    /**
     * 用户姓名
     */
    @ApiModelProperty("用户姓名")
    private String name;

    /**
     * 用户头像
     */
    @ApiModelProperty("用户头像")
    private String portrait;

    /**
     * 用户班级
     */
    @ApiModelProperty("用户班级")
    private String club;

    /**
     * 电话号码
     */
    @ApiModelProperty("电话号码")
    private String telephone;

    /**
     * 角色名称
     */
    @ApiModelProperty("角色名称")
    private String roleName;

    /**
     * 权限集合
     */
    @ApiModelProperty("权限集合")
    private List<PermissionsInfo> permissions;
}
