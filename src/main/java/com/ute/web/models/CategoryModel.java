package com.ute.web.models;

import com.ute.web.beans.Category;
import com.ute.web.beans.Product;
import com.ute.web.utils.DbUtils;
import org.sql2o.Connection;

import java.util.List;

public class CategoryModel {
    public static List<Category> findAll() {
        final String query = "select * from categories";
        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .executeAndFetch(Category.class);
        }
    }

    //Admin
    public static void add(Category c) {
        String Sql = "INSERT INTO categories (CatName , GrCatID) VALUES (:CatName ,:GrCatID)";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(Sql)
                    .addParameter("CatName", c.getCatName())
                    .addParameter("GrCatID", c.getGrCatID())
                    .executeUpdate();
        }
    }

    public static Category findById(int id) {
        final String query = "select * from categories where CatID =:CatID";
        try (Connection con = DbUtils.getConnection()) {
            List<Category> list = con.createQuery(query)
                    .addParameter("CatID", id)
                    .executeAndFetch(Category.class);
            if (list.size() == 0) {
                return null;
            }
            return list.get(0);
        }
    }

    public static List<Category> findByGroupCatID(int GrCatId) {
        final String query = "select * from categories where GrCatID =:GrCatID";
        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .addParameter("GrCatID", GrCatId)
                    .executeAndFetch(Category.class);
        }
    }

    public static void update(Category c) {
        String Sql = "UPDATE categories set CatName = :CatName , GrCatID = :GrCatID where CatID = :CatID";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(Sql)
                    .addParameter("CatName", c.getCatName())
                    .addParameter("GrCatID", c.getGrCatID())
                    .addParameter("CatID", c.getCatID())
                    .executeUpdate();
        }
    }

    public static void delete(int id) {
        String Sql = "DELETE from categories where CatID = :CatID";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(Sql)
                    .addParameter("CatID", id)
                    .executeUpdate();
        }
    }
    //End
}
