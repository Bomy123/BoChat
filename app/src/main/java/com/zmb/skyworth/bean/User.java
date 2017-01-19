package com.zmb.skyworth.bean;

/**
 * Created by zhangmingbao on 16-11-4.
 */

public class User {
    private String username;
    private String password;
    private String photo;
    private int age;
    private int sex;

    public int getAge() {
        return age;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoto() {
        return photo;
    }

    public int getSex() {
        return sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
