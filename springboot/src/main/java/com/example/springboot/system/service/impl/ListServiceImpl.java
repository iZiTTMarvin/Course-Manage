package com.example.springboot.system.service.impl;

import com.example.springboot.system.entity.ListEntity;
import com.example.springboot.system.mapper.ListMapper;
import com.example.springboot.system.service.ListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程表 服务实现类
 * </p>
 *
 * @author he
 * @since 2024-05-21
 */
@Service
public class ListServiceImpl extends ServiceImpl<ListMapper, ListEntity> implements ListService {

}
