package com.kevin.spring.Iocdemo.dao;

import com.kevin.spring.Iocdemo.entity.User;

/**
 * @Author kevin
 * @Description
 * @Date Created on 2019/12/27 14:13
 */
public interface UserDAO {

    void save(User u);
    void delete(User u);

}
