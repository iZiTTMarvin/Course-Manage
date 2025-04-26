package com.example.springboot.system.resp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 课程表
 * </p>
 *
 * @author he
 * @since 2024-05-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("course_list")
@Schema(name = "ListEntity", description = "$!{table.comment}")
public class ListResp extends Model<ListResp> {

    @Schema(description = "编号")
    @TableId(value = "list_id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "名称")
    @TableField("list_name")
    private String name;

    @Schema(description = "学分")
    @TableField("list_grade")
    private String grade;

    @Schema(description = "类型@")
    @TableField("list_type")
    private Integer type;

    private String tname;

    @Schema(description = "所属专业@")
    @TableField("list_profess")
    private Integer profess;

    private String pname;

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
