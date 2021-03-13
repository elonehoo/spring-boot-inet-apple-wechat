package com.inet.code.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 全部用户信息的权限
 * @author HCY
 * @since 2021/3/5 上午9:54
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@ApiModel("全部用户信息的权限")
public class PermissionsInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限主键
     */
    @ApiModelProperty("权限主键")
    private String id;

    /**
     * 权限名称
     */
    @ApiModelProperty("权限名称")
    private String permissionsName;


}
