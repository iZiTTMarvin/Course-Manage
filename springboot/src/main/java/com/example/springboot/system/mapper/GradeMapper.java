package com.example.springboot.system.mapper;

import com.example.springboot.system.entity.GradeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 学生成绩 Mapper 接口
 * </p>
 *
 * @author he
 * @since 2024-05-22
 */
@Mapper
public interface GradeMapper extends BaseMapper<GradeEntity> {

}
