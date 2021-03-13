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
 * 注册的实体类
 * @author HCY
 * @since 2021/3/5 下午2:20
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("注册的实体类")
public class Register implements Serializable {
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
     * 用户密码
     */
    @ApiModelProperty("用户密码")
    private String cipher;

}
