package com.ute.web.models;

import com.ute.web.beans.Category;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class CategoryModel {
    public static List<Category> findAll() {
        Sql2o sql2o = new Sql2o("jdbc:mysql://localhost:3306/abc", "root", "");
        final String query = "select * from categories";
        try (Connection con = sql2o.open()) {
            return con.createQuery(query)
                    .executeAndFetch(Category.class);
        }
    }

    public static void add(Category c) {
        Sql2o sql2o = new Sql2o("jdbc:mysql://localhost:3306/abc", "root", "");
        String insertSql ="INSERT INTO categories (CatName) VALUES (:catname)";
        try (Connection con = sql2o.open()) {
            con.createQuery(insertSql)
                    .addParameter("catname", c.getCatName())
                    .executeUpdate();
        }
    }

    }
