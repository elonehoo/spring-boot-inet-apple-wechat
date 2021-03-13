package com.inet.code.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 修改用户信息
 * @author HCY
 * @since 2021/3/6 下午4:44
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("修改用户信息")
public class Amend {

    /**
     * 旧密码
     */
    @ApiModelProperty("旧密码")
    private String oldPassword;

    /**
     * 1新密码
     */
    @ApiModelProperty("1新密码")
    private String newOnePassword;

    /**
     * 2新密码
     */
    @ApiModelProperty("2新密码")
    private String newTwoPassword;
}
