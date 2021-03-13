package com.inet.code.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 登陆的实体类
 * @author HCY
 * @since 2021/3/5 下午9:35
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("注册的实体类")
public class Login implements Serializable {

    /**
     * 登陆的账号
     */
    @ApiModelProperty("登陆的账号")
    private String account;

    /**
     * 登陆的密码
     */
    @ApiModelProperty("登陆的密码")
    private String password;

}
