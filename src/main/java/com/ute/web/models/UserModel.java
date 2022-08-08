package com.ute.web.models;

import com.ute.web.beans.Product;
import com.ute.web.beans.User;
import com.ute.web.utils.DbUtils;
import org.sql2o.Connection;

import java.util.List;


public class UserModel {
    public static void add(User u) {
        String Sql = "INSERT INTO users (username, password, name, email, dob, permission , code) VALUES (:username,:password,:name,:email,:dob,:permission,:code)";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(Sql)
                    .addParameter("username", u.getUsername())
                    .addParameter("password",u.getPassword())
                    .addParameter("name", u.getName())
                    .addParameter("email",u.getEmail())
                    .addParameter("dob",u.getDob())
                    .addParameter("permission",u.getPermission())
                    .addParameter("code" , u.getCode() )
                    .executeUpdate();
        }
    }

    public static User findByUsername(String username) {
        final String query = "select * from users where username=:username";
        try (Connection con = DbUtils.getConnection()) {
            List<User> list = con.createQuery(query)
                    .addParameter("username", username)
                    .executeAndFetch(User.class);
            if (list.size() == 0) {
                return null;
            }
            return list.get(0);
        }
    }

    public static User findByCode(int code) {
        final String query = "select * from users where code=:code";
        try (Connection con = DbUtils.getConnection()) {
            List<User> list = con.createQuery(query)
                    .addParameter("code", code)
                    .executeAndFetch(User.class);
            if (list.size() == 0) {
                return null;
            }
            return list.get(0);
        }
    }

    public static void update(User u) {
        String Sql = "UPDATE users SET  Username = :Username, Password = :Password, Name = :Name, Email = :Email, Dob = :Dob, Permission = :Permission , Code = :Code WHERE Id = :Id";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(Sql)
                    .addParameter("Username", u.getUsername())
                    .addParameter("Password", u.getPassword())
                    .addParameter("Name", u.getName())
                    .addParameter("Email", u.getEmail())
                    .addParameter("Dob", u.getDob())
                    .addParameter("Permission", u.getPermission())
                    .addParameter("Code" , u.getCode())
                    .addParameter("Id", u.getId())
                    .executeUpdate();
        }
    }
}
