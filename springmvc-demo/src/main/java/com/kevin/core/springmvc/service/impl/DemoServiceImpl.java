package com.kevin.core.springmvc.service.impl;

import com.kevin.core.springmvc.annotation.KevinService;
import com.kevin.core.springmvc.service.DemoService;

/**
 * @Author kevin
 * @Description 接口实现类
 * @Date Created on 2019/12/31 9:37
 */

@KevinService
public class DemoServiceImpl implements DemoService {
    @Override
    public void add(String name) {
        System.err.println("DemoServiceImpl add--->" + name);
    }

    @Override
    public void delete(String name) {
        System.err.println("DemoServiceImpl delete--->" + name);
    }
}

