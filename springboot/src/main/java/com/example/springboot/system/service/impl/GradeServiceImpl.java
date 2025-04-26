package com.example.springboot.system.service.impl;

import com.example.springboot.system.entity.GradeEntity;
import com.example.springboot.system.mapper.GradeMapper;
import com.example.springboot.system.service.GradeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生成绩 服务实现类
 * </p>
 *
 * @author he
 * @since 2024-05-22
 */
@Service
public class GradeServiceImpl extends ServiceImpl<GradeMapper, GradeEntity> implements GradeService {

}
