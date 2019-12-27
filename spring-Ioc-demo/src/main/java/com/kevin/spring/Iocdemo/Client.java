package com.kevin.spring.Iocdemo;

import com.kevin.spring.Iocdemo.entity.User;
import com.kevin.spring.Iocdemo.service.UserServiceImpl;
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

            UserServiceImpl userService =  (UserServiceImpl)beanFactory.getBean("userService");
            User user =  (User)beanFactory.getBean("user");

            user.setUserName("kevin");
            user.setPassword("hhhhhhh");
            userService.addUser(user);

            userService.delUser(user);
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


    }

}
