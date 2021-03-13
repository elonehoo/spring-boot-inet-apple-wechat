package com.inet.code.entity.po;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 签到表
 * </p>
 *
 * @author HCY
 * @since 2021-03-04
 */

@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tbl_check")
public class Check implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id" , type = IdType.UUID)
    private String id;

    /**
     * 学生序号
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 签到状态
     */
    @TableField(value = "checkState")
    private Integer checkState;

    /**
     * 开始时间
     */
    @TableField("startTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    /**
     * 结束时间
     */
    @TableField("endTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    /**
     * 签到总时间
     */
    @TableField("checkTotal")
    private Long checkTotal;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_create",fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @TableField(value = "gmt_modified",fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtModified;

    public Check(String userId, Integer checkState, Long checkTotal) {
        this.userId = userId;
        this.checkState = checkState;
        this.checkTotal = checkTotal;
    }

    public Check(String userId, Integer checkState, Date startTime) {
        this.userId = userId;
        this.checkState = checkState;
        this.startTime = startTime;
    }

    public Check(String userId, Integer checkState, Date endTime, Long checkTotal) {
        this.userId = userId;
        this.checkState = checkState;
        this.endTime = endTime;
        this.checkTotal = checkTotal;
    }
}
