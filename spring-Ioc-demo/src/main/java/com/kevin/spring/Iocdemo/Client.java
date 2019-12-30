package com.kevin.spring.Iocdemo;

import com.kevin.spring.Iocdemo.entity.Student;
import com.kevin.spring.Iocdemo.entity.User;
import com.kevin.spring.Iocdemo.service.StudentServiceImpl;
import com.kevin.spring.Iocdemo.service.UserServiceImpl;
import com.kevin.spring.Iocdemo.spring.BeanFactory;
import com.kevin.spring.Iocdemo.spring.ClassPathXmlApplicationContext;
import org.jdom.JDOMException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author kevin
 * @Description
 * @Date Created on 2019/12/27 14:10
 */
public class Client {

    public static void main(String[] args) {

        try {
            BeanFactory beanFactory = new ClassPathXmlApplicationContext();

            /*UserServiceImpl userService =  (UserServiceImpl)beanFactory.getBean("userService");
            User user =  (User)beanFactory.getBean("user");
            user.setUserName("kevin");
            user.setPassword("hhhhhhh");
            userService.addUser(user);
            userService.delUser(user);

            System.err.println(userService.toString());
            System.err.println();

            UserServiceImpl userService2 =  (UserServiceImpl)beanFactory.getBean("userService");

            System.err.println(userService2.toString());*/

            /***********************************************************************************/

            StudentServiceImpl studentService =  (StudentServiceImpl)beanFactory.getBean("studentService");
            Student student =  (Student)beanFactory.getBean("student");
            student.setUserName("张三");
            student.setPassword("123456");

            studentService.addStudent(student);

            studentService.delStudent(student);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
