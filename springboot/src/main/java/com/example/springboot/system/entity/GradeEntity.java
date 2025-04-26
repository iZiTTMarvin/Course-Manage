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
 * 学生成绩
 * </p>
 *
 * @author he
 * @since 2024-05-22
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("course_grade")
@Schema(name = "GradeEntity", description = "$!{table.comment}")
public class GradeEntity extends Model<GradeEntity> {

    @Schema(description = "编号")
    @TableId(value = "grade_id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "用户@")
    @TableField("grade_user")
    private Integer user;

    @Schema(description = "课程@")
    @TableField("grade_list")
    private Integer list;

    @Schema(description = "分数")
    @TableField("grade_score")
    private Integer score;

    @Schema(description = "学分")
    @TableField("grade_grade")
    private Integer grade;

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
