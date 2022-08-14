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

    public static int findUserID (int UserID) {
        final String query = "select * from auctions where UserID=:UserID";
        try (Connection con = DbUtils.getConnection()) {
            List<Auction> list = con.createQuery(query)
                    .addParameter("UserID", UserID)
                    .executeAndFetch(Auction.class);
            if (list.size() == 0) {
                return -1;
            }
            return list.get(0).getAuID();
        }
    }

    public static List<Auction> findTopHighestPrice () {
        final String query = "SELECT * FROM auctions order by Price desc";
        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .executeAndFetch(Auction.class);
        }
    }


}
