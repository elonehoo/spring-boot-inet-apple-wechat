package com.inet.code.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 签到排名
 * @author HCY
 * @since 2021/3/8 下午7:57
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@ApiModel("签到排名")
public class Ranking {

    /**
     * 用户序号
     */
    private String userId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 用户学号
     */
    private String userNumber;

    /**
     * 用户签到时间
     */
    private String checkTotal;
}
