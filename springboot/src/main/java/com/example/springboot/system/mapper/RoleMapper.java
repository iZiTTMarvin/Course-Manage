package com.example.springboot.system.mapper;

import com.example.springboot.system.entity.RoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author he
 * @since 2024-05-20
 */
@Mapper
public interface RoleMapper extends BaseMapper<RoleEntity> {

}
