package com.example.springboot.system.service.impl;

import com.example.springboot.system.entity.TableEntity;
import com.example.springboot.system.mapper.TableMapper;
import com.example.springboot.system.service.TableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课表 服务实现类
 * </p>
 *
 * @author he
 * @since 2024-05-20
 */
@Service
public class TableServiceImpl extends ServiceImpl<TableMapper, TableEntity> implements TableService {

}
