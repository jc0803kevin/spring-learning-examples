package com.kevin.spring.Iocdemo.dao;

import com.kevin.spring.Iocdemo.entity.Student;

/**
 * @Author kevin
 * @Description
 * @Date Created on 2019/12/27 15:56
 */
public class StudentDAOImpl implements StudentDAO {
    @Override
    public void save(Student u) {
        System.out.println("add Student:" + u.toString());
    }

    @Override
    public void delete(Student u) {
        System.out.println("add Student:" + u.toString());
    }
}
