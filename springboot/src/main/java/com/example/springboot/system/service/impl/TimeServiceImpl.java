package com.example.springboot.system.service.impl;

import com.example.springboot.system.entity.TimeEntity;
import com.example.springboot.system.mapper.TimeMapper;
import com.example.springboot.system.service.TimeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程类型表 服务实现类
 * </p>
 *
 * @author he
 * @since 2024-05-20
 */
@Service
public class TimeServiceImpl extends ServiceImpl<TimeMapper, TimeEntity> implements TimeService {

}
