package com.example.springboot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.system.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author he
 * @since 2024-05-20
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

    /**
     * 根据条件查询用户
     * @param params 查询参数
     * @return 用户列表
     */
    List<UserEntity> findUserByCondition(Map<String, Object> params);
}
