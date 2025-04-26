package com.example.springboot.system.mapper;

import com.example.springboot.system.entity.ScheduleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 班级课表 Mapper 接口
 * </p>
 *
 * @author he
 * @since 2024-05-22
 */
@Mapper
public interface ScheduleMapper extends BaseMapper<ScheduleEntity> {

}
