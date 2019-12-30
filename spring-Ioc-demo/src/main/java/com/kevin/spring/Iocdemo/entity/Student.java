package com.kevin.spring.Iocdemo.entity;

/**
 * @Author kevin
 * @Description
 * @Date Created on 2019/12/27 15:55
 */
public class Student {

    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Student{" +
                "userName=" + userName +
                ", password=" + password +
                '}';
    }

}
