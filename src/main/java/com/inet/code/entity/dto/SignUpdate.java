package com.inet.code.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 同意用户签到或者拒绝用户签到
 * @author HCY
 * @since 2021/3/8 下午4:44
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("同意用户签到或者拒绝用户签到")
public class SignUpdate {

    /**
     * 用户序号
     */
    @ApiModelProperty("用户序号")
    private String userId;
}
