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
    private String serviceName;

    //必须显示定义无参构造方法
    public StudentServiceImpl(){}

    /**
     * @Author kevin
     * @Description 通过构造方法的形式注入
     * @Date Created on 2019/12/27 16:07
     * @param  studentDAO
     */
    public StudentServiceImpl(StudentDAO studentDAO, String serviceName){
        this.studentDAO = studentDAO;
        this.serviceName = serviceName;
    }

    public void addStudent(Student student){
        studentDAO.save(student);
    }

    public void delStudent(Student student){
        studentDAO.delete(student);
    }

    @Override
    public String toString() {
        return "StudentServiceImpl{" +
                "studentDAO =" + studentDAO +
                ", serviceName=" + serviceName +
                '}';
    }
}
