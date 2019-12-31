package com.kevin.core.springmvc.controller;

import com.kevin.core.springmvc.annotation.KevinAutowired;
import com.kevin.core.springmvc.annotation.KevinController;
import com.kevin.core.springmvc.annotation.KevinRequestMapping;
import com.kevin.core.springmvc.annotation.KevinRequestParam;
import com.kevin.core.springmvc.service.DemoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author kevin
 * @Description 控制器接口
 * @Date Created on 2019/12/31 9:29
 */

@KevinController
public class DemoController {

    @KevinAutowired
    private DemoService demoService;

    @KevinRequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, String name){
        demoService.add(name);

        try {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("Controller add " + name);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @KevinRequestMapping("/del")
    public void del(HttpServletRequest request, HttpServletResponse response,String name){
        demoService.delete(name);

        try {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("Controller del "+name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
