package com.inet.code.entity.po;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户角色
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
@TableName("tbl_role_user")
public class RoleUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户权限序号
     */
    @TableId(value = "id" , type = IdType.UUID)
    private String id;

    /**
     * 用户序号
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 权限的序号
     */
    @TableField(value = "role_id")
    private String roleId;

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

    public RoleUser(String userId, String roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

}
