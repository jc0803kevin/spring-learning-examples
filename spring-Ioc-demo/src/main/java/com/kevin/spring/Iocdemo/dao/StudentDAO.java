package com.kevin.spring.Iocdemo.dao;

import com.kevin.spring.Iocdemo.entity.Student;

/**
 * @Author kevin
 * @Description 持久层接口
 * @Date Created on 2019/12/27 15:55
 */
public interface StudentDAO {

    void save(Student u);

    void delete(Student u);

}
