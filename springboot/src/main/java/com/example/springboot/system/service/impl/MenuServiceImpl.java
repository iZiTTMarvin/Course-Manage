package com.example.springboot.system.service.impl;

import com.example.springboot.system.entity.MenuEntity;
import com.example.springboot.system.mapper.MenuMapper;
import com.example.springboot.system.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author he
 * @since 2024-05-21
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuEntity> implements MenuService {

}
