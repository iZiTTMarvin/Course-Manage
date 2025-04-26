package com.example.springboot.system.service.impl;

import com.example.springboot.system.entity.TypeEntity;
import com.example.springboot.system.mapper.TypeMapper;
import com.example.springboot.system.service.TypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程类型表 服务实现类
 * </p>
 *
 * @author he
 * @since 2024-05-21
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, TypeEntity> implements TypeService {

}
