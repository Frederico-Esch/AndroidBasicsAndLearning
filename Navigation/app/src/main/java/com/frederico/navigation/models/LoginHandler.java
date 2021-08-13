package com.frederico.navigation.models;

import android.os.Parcelable;

import java.io.Serializable;

public class LoginHandler implements Serializable {
    String Name;
    String Age;
    String Email;
    LoginClickListener loginClickListener;

    public LoginHandler(String name, String age, String email) {
        Name = name;
        Age = age;
        Email = email;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setAge(String age) {
        Age = age;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public String getAge() {
        return Age;
    }

    public String getEmail() {
        return Email;
    }

    public LoginClickListener getLoginClickListener() {
        return loginClickListener;
    }

    public void setLoginClickListener(LoginClickListener loginClickListener) {
        this.loginClickListener = loginClickListener;
    }

    public interface LoginClickListener{
        void click(String name, String Age, String Email);
    }
}
