package com.example.springboot.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 关联表
 * </p>
 *
 * @author he
 * @since 2024-05-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("course_rm")
@Schema(name = "RmEntity", description = "$!{table.comment}")
public class RmEntity extends Model<RmEntity> {

    @Schema(description = "编号")
    @TableId(value = "rm_id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "角色")
    @TableField("rm_role")
    private Integer role;

    @Schema(description = "菜单")
    @TableField("rm_menu")
    private Integer menu;

    @Schema(description = "创建者")
    @TableField("create_by")
    private String createBy;

    @Schema(description = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @Schema(description = "更新者")
    @TableField("update_by")
    private String updateBy;

    @Schema(description = "更新时间")
    @TableField("update_time")
    private Date updateTime;

    @Schema(description = "伪删除")
    @TableField("undock")
    private Integer undock;

    @Schema(description = "状态")
    @TableField("state")
    private Integer state;

    @Schema(description = "备注")
    @TableField("remark")
    private String remark;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
