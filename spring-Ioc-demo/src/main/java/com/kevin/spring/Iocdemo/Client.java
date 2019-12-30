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

        Client client = new Client();
        //通过构造方法注入
        client.injectionByConstructor();

        //通过Getter/Setter方法注入
        client.injectionByGetterAndSetter();


    }

    private void injectionByConstructor(){
        try {

            /*******************************通过构造方法注入****************************************************/
            BeanFactory beanFactory = new ClassPathXmlApplicationContext();
            StudentServiceImpl studentService =  (StudentServiceImpl)beanFactory.getBean("studentService");
            Student student =  (Student)beanFactory.getBean("student");
            student.setUserName("张三");
            student.setPassword("123456");

            System.err.println(studentService.toString());
            System.err.println(student.toString());

            studentService.addStudent(student);

            studentService.delStudent(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void injectionByGetterAndSetter() {
        /*******************************通过Getter/Setter方法注入****************************************************/
        BeanFactory beanFactory = null;
        try {
            beanFactory = new ClassPathXmlApplicationContext();
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        UserServiceImpl userService =  (UserServiceImpl)beanFactory.getBean("userService");
        User user =  (User)beanFactory.getBean("user");
        user.setUserName("kevin");
        user.setPassword("hhhhhhh");
        userService.addUser(user);
        userService.delUser(user);

        System.err.println(userService.toString());
        System.err.println();

        UserServiceImpl userService2 =  (UserServiceImpl)beanFactory.getBean("userService");

        System.err.println(userService2.toString());
    }


}
