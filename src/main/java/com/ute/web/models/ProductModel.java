package com.ute.web.models;

import com.ute.web.beans.Category;
import com.ute.web.beans.Product;
import com.ute.web.utils.DbUtils;
import org.sql2o.Connection;

import java.util.List;

public class ProductModel {
    public static List<Product> findAll() {
        final String query = "select * from products";
        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .executeAndFetch(Product.class);
        }
    }

    //Admin
    public static void add(Product p) {
        String Sql = "INSERT INTO products ( ProName, Price, CatID, Quantity, TinyDes, FullDes) VALUES (:ProName,:Price,:CatID,:Quantity,:TinyDes,:FullDes)";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(Sql)
                    .addParameter("ProName", p.getProName())
                    .addParameter("Price", p.getPrice())
                    .addParameter("CatID", p.getCatID())
                    .addParameter("Quantity", p.getQuantity())
                    .addParameter("TinyDes", p.getTinyDes())
                    .addParameter("FullDes", p.getFullDes())
                    .executeUpdate();
        }
    }


    public static Product findById(int id) {
        final String query = "select * from products where ProID =:ProID";
        try (Connection con = DbUtils.getConnection()) {
            List<Product> list = con.createQuery(query)
                    .addParameter("ProID", id)
                    .executeAndFetch(Product.class);
            if (list.size() == 0) {
                return null;
            }
            return list.get(0);
        }
    }

    public static void update(Product p) {
        String Sql = "UPDATE products SET  ProName = :ProName, Price = :Price, CatID = :CatID, Quantity = :Quantity, TinyDes = :TinyDes, FullDes = :FullDes WHERE ProID = :ProID";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(Sql)
                    .addParameter("ProName", p.getProName())
                    .addParameter("Price", p.getPrice())
                    .addParameter("CatID", p.getCatID())
                    .addParameter("Quantity", p.getQuantity())
                    .addParameter("TinyDes", p.getTinyDes())
                    .addParameter("FullDes", p.getFullDes())
                    .addParameter("ProID", p.getProID())
                    .executeUpdate();
        }
    }

    public static void delete(int id) {
        String Sql = "DELETE from products where ProID = :ProID";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(Sql)
                    .addParameter("ProID", id)
                    .executeUpdate();
        }
    }

    //End
    //User
    public static List<Product> findByCatID(int catId) {
        final String query = "select * from products where CatID =:CatID";
        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .addParameter("CatID", catId)
                    .executeAndFetch(Product.class);
        }
    }
    //End

    public static List<Product> findTop5HighestPrice () {
        final String query = "SELECT * FROM products order by Price desc limit 5";
        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .executeAndFetch(Product.class);
        }
    }

}
