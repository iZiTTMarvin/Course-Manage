package com.example.springboot.system.service.impl;

import com.example.springboot.system.entity.UserEntity;
import com.example.springboot.system.mapper.UserMapper;
import com.example.springboot.system.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author he
 * @since 2024-05-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

}
