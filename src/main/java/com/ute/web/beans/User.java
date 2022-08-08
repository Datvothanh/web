package com.ute.web.beans;

import java.time.LocalDate;

public class User {
    //0:Admin
    //1:Ban
    //2:mua
    //3:khach
    //4:khach can xac nhan
    private int id , permission , code;
    private String username , password , name , email;
    private LocalDate dob;

    public User() {
    }

    public User(int id, int permission, int code, String username, String password, String name, String email, LocalDate dob) {
        this.id = id;
        this.permission = permission;
        this.code = code;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public User(int permission, int code ,String username, String password, String name, String email, LocalDate dob) {
        this.id = -1;
        this.permission = permission;
        this.code = code;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public int getId() {
        return id;
    }

    public int getCode() {
        return code;
    }

    public int getPermission() {
        return permission;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDob() {
        return dob;
    }
}
