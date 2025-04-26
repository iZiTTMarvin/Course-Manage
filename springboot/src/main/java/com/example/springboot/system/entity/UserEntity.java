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
 * 用户表
 * </p>
 *
 * @author he
 * @since 2024-05-20
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("course_user")
@Schema(name = "UserEntity", description = "$!{table.comment}")
public class UserEntity extends Model<UserEntity> {

    @Schema(description = "编号")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "头像")
    @TableField("user_pic")
    private String pic;

    @Schema(description = "名称")
    @TableField("user_name")
    private String name;

    @Schema(description = "性别")
    @TableField("user_gender")
    private String gender;

    @Schema(description = "生日")
    @TableField("user_birth")
    private String birth;

    @Schema(description = "账号")
    @TableField("user_acc")
    private String acc;

    @Schema(description = "密码")
    @TableField("user_pwd")
    private String pwd;

    @Schema(description = "角色@")
    @TableField("user_role")
    private int role;

    @Schema(description = "入学年份")
    @TableField("user_start")
    private String startYear;

    @Schema(description = "学院")
    @TableField("user_academic")
    private String academic;

    @Schema(description = "专业")
    @TableField("user_profess")
    private String profess;

    @Schema(description = "班级")
    @TableField("user_team")
    private String team;

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
