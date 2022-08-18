package com.ute.web.models;

import com.ute.web.beans.Auction;
import com.ute.web.beans.Product;
import com.ute.web.beans.User;
import com.ute.web.utils.DbUtils;
import org.sql2o.Connection;

import java.util.List;

public class AuctionModel {

    public static List<Auction> findAll() {
        final String query = "select * from auctions";
        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .executeAndFetch(Auction.class);
        }
    }
    public static void add(Auction a) {
        String Sql = "INSERT INTO auctions (UserID, ProID, Price) VALUES (:UserID,:ProID,:Price)";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(Sql)
                    .addParameter("UserID", a.getUserID())
                    .addParameter("ProID", a.getProID())
                    .addParameter("Price", a.getPrice())
                    .executeUpdate();
        }
    }

    public static void update(Auction a) {
        String Sql = "UPDATE auctions SET  UserID = :UserID, ProID = :ProID, Price = :Price WHERE AuID = :AuID";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(Sql)
                    .addParameter("UserID", a.getUserID())
                    .addParameter("ProID", a.getProID())
                    .addParameter("Price", a.getPrice())
                    .addParameter("AuID" , a.getAuID())
                    .executeUpdate();
        }
    }

    public static int findUserIDProID (int UserID , int ProID) {
        final String query = "select * from auctions where UserID=:UserID AND ProID=:ProID";
        try (Connection con = DbUtils.getConnection()) {
            List<Auction> list = con.createQuery(query)
                    .addParameter("UserID", UserID)
                    .addParameter("ProID", ProID)
                    .executeAndFetch(Auction.class);
            if (list.size() == 0) {
                return -1;
            }
            return list.get(0).getAuID();
        }
    }

    public static int findSizeProID (int ProID) {
        final String query = "select * from auctions where ProID=:ProID";
        try (Connection con = DbUtils.getConnection()) {
            List<Auction> list = con.createQuery(query)
                    .addParameter("ProID", ProID)
                    .executeAndFetch(Auction.class);
            return list.size();
        }
    }


    public static List<Auction> findTopHighestPrice () {
        final String query = "SELECT * FROM auctions order by Price desc";
        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .executeAndFetch(Auction.class);
        }
    }

    public static Auction findSecondHighestPrice (int ProID) {
        final String query = "SELECT * FROM auctions WHERE ProID=:ProID order by Price desc ";
        try (Connection con = DbUtils.getConnection()) {
            List<Auction> list = con.createQuery(query)
                    .addParameter("ProID", ProID)
                    .executeAndFetch(Auction.class);
            return list.get(1);
        }
    }

    public static void delete(int id) {
        String Sql = "DELETE from auctions where AuID = :AuID";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(Sql)
                    .addParameter("AuID", id)
                    .executeUpdate();
        }
    }

}
