package com.kevin.spring.Iocdemo.service;

import com.kevin.spring.Iocdemo.dao.StudentDAO;
import com.kevin.spring.Iocdemo.entity.Student;

/**
 * @Author kevin
 * @Description
 * @Date Created on 2019/12/27 15:57
 */
public class StudentServiceImpl {

    private StudentDAO studentDAO;


    //必须显示定义无参构造方法
    public StudentServiceImpl(){}
    /**
     * @Author kevin
     * @Description 通过构造方法的形式注入
     * @Date Created on 2019/12/27 16:07
     * @param  studentDAO
     * @return
     */
    public StudentServiceImpl(StudentDAO studentDAO){
        this.studentDAO = studentDAO;
    }

    public void addStudent(Student student){
        studentDAO.save(student);
    }

    public void delStudent(Student student){
        studentDAO.delete(student);
    }

}
