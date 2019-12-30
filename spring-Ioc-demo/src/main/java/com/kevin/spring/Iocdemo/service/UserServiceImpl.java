package com.kevin.spring.Iocdemo.service;

import com.kevin.spring.Iocdemo.dao.UserDAO;
import com.kevin.spring.Iocdemo.entity.User;

/**
 * @Author kevin
 * @Description 业务层
 * @Date Created on 2019/12/27 14:15
 */
public class UserServiceImpl {

    //需要注入的对象，通过getter/setter的方式注入
    private UserDAO userDAO;

    public void addUser(User user){
        userDAO.save(user);
    }

    public void delUser(User user){
        userDAO.delete(user);
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
