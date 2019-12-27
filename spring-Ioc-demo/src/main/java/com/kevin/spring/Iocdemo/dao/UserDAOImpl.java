package com.kevin.spring.Iocdemo.dao;

import com.kevin.spring.Iocdemo.entity.User;

/**
 * @Author kevin
 * @Description
 * @Date Created on 2019/12/27 14:14
 */
public class UserDAOImpl implements UserDAO {
    public void save(User u) {
        System.out.println("add User:" + u.toString());
    }

    public void delete(User u) {
        System.out.println("delete User: " + u.toString());
    }
}
