package com.ute.web.models;

import com.ute.web.beans.Product;
import com.ute.web.beans.User;
import com.ute.web.utils.DbUtils;
import org.sql2o.Connection;

import java.util.List;


public class UserModel {
    public static void add(User u) {
        String Sql = "INSERT INTO users (username, password, name, email, dob, permission) VALUES (:username,:password,:name,:email,:dob,:permission)";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(Sql)
                    .addParameter("username", u.getUsername())
                    .addParameter("password",u.getPassword())
                    .addParameter("name", u.getName())
                    .addParameter("email",u.getEmail())
                    .addParameter("dob",u.getDob())
                    .addParameter("permission",u.getPermission())
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
}
