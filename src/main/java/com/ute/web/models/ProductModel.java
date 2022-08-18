package com.ute.web.models;

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
        String Sql = "INSERT INTO products (ProName, StartingPrice, CatID, TinyDes, FullDes, StepPrice, HighestPaidPrice, AutoExtend, StartDay, EndDay,UserID,Sell ,NowPrice,CountAuction,UserSellID) VALUES (:ProName,:StartingPrice,:CatID,:TinyDes,:FullDes,:StepPrice,:HighestPaidPrice,:AutoExtend,:StartDay,:EndDay,:UserID,:Sell,:NowPrice,:CountAuction,:UserSellID) ";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(Sql)
                    .addParameter("ProName", p.getProName())
                    .addParameter("StartingPrice", p.getStartingPrice())
                    .addParameter("CatID", p.getCatID())
                    .addParameter("TinyDes", p.getTinyDes())
                    .addParameter("FullDes", p.getFullDes())
                    .addParameter("StepPrice", p.getStepPrice())
                    .addParameter("HighestPaidPrice", p.getHighestPaidPrice())
                    .addParameter("AutoExtend", p.getAutoExtend())
                    .addParameter("StartDay", p.getStartDay())
                    .addParameter("EndDay", p.getEndDay())
                    .addParameter("UserID", p.getUserID())
                    .addParameter("Sell", p.getSell())
                    .addParameter("NowPrice", p.getNowPrice())
                    .addParameter("CountAuction", p.getCountAuction())
                    .addParameter("UserSellID", p.getUserSellID())
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
        String Sql = "UPDATE products SET  ProName = :ProName, StartingPrice = :StartingPrice, CatID = :CatID, TinyDes = :TinyDes, FullDes = :FullDes, StepPrice = :StepPrice, HighestPaidPrice = :HighestPaidPrice, AutoExtend = :AutoExtend, StartDay = :StartDay, EndDay = :EndDay , NowPrice = :NowPrice WHERE ProID = :ProID";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(Sql)
                    .addParameter("ProName", p.getProName())
                    .addParameter("StartingPrice", p.getStartingPrice())
                    .addParameter("CatID", p.getCatID())
                    .addParameter("TinyDes", p.getTinyDes())
                    .addParameter("FullDes", p.getFullDes())
                    .addParameter("StepPrice", p.getStepPrice())
                    .addParameter("HighestPaidPrice", p.getHighestPaidPrice())
                    .addParameter("AutoExtend", p.getAutoExtend())
                    .addParameter("EndDay", p.getEndDay())
                    .addParameter("StartDay", p.getStartDay())
                    .addParameter("NowPrice", p.getNowPrice())
                    .addParameter("ProID", p.getProID())
                    .executeUpdate();
        }
    }

    public static void updateHighestPaidPrice(Product p) {
        String Sql = "UPDATE products SET  HighestPaidPrice = :HighestPaidPrice , UserID = :UserID , CountAuction =:CountAuction WHERE ProID = :ProID";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(Sql)
                    .addParameter("HighestPaidPrice", p.getHighestPaidPrice())
                    .addParameter("UserID", p.getUserID())
                    .addParameter("CountAuction",p.getCountAuction())
                    .addParameter("ProID", p.getProID())
                    .executeUpdate();
        }
    }

    public static void updateCountAuction(Product p) {
        String Sql = "UPDATE products SET  CountAuction =:CountAuction WHERE ProID = :ProID";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(Sql)
                    .addParameter("CountAuction",p.getCountAuction())
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

    public static List<Product> findTop5HighestPrice() {
        final String query = "SELECT * FROM products order by StartingPrice desc limit 5";
        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .executeAndFetch(Product.class);
        }
    }

    public static List<Product> findTop5HighestCountAuction() {
        final String query = "SELECT * FROM products order by CountAuction desc limit 5";
        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .executeAndFetch(Product.class);
        }
    }

    public static List<Product> findByCatID5(int catId , int proId) {
        final String query = "select * from products where CatID =:CatID AND NOT ProID =:ProID limit 5";
        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .addParameter("CatID", catId)
                    .addParameter("ProID", proId)
                    .executeAndFetch(Product.class);
        }
    }

    public static Product ProEnd() {
        final String query = "SELECT * FROM products ORDER BY ProID desc LIMIT 1";
        try (Connection con = DbUtils.getConnection()) {
            List<Product> list = con.createQuery(query)
                    .executeAndFetch(Product.class);
            if (list.size() == 0) {
                return null;
            }
            return list.get(0);
        }
    }

}
