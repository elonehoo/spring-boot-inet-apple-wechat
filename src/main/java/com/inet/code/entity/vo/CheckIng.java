package com.inet.code.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 展示签到的具体模块
 * @author HCY
 * @since 2021/3/8 上午10:16
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@ApiModel("展示签到的具体模块")
public class CheckIng {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private String userId;

    /**
     * 学生姓名
     */
    @ApiModelProperty("学生姓名")
    private String name;

    /**
     * 学生学号
     */
    @ApiModelProperty("学生学号")
    private String number;

    /**
     * 学生状态
     */
    @ApiModelProperty("学生状态")
    private String state;
}
