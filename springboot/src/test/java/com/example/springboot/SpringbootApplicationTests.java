package com.example.springboot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot.system.entity.MenuEntity;
import com.example.springboot.system.mapper.MenuMapper;
import com.example.springboot.system.service.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class SpringbootApplicationTests {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private MenuService menuService;

    @Test
    public void test() {
        QueryWrapper<MenuEntity> wrapper = new QueryWrapper<>();
        wrapper.ne("menu_parent", 0);
        List<MenuEntity> list = menuMapper.selectList(wrapper);

        // 使用 Stream API 按 ranks 字段排序
        List<MenuEntity> sortedDataItems = list.stream()
                .sorted((item1, item2) -> Integer.compare(item1.getRanks(), item2.getRanks())).toList();

        for (int i = 0; i < sortedDataItems.size(); i++) {
            System.out.println(sortedDataItems.get(i).getRanks());
        }
    }

    @Test
    public void contextLoads() {

    }

}
