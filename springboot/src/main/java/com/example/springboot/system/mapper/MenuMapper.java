package com.example.springboot.system.mapper;

import com.example.springboot.system.entity.MenuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author he
 * @since 2024-05-21
 */
@Mapper
public interface MenuMapper extends BaseMapper<MenuEntity> {

}
