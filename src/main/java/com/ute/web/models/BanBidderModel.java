package com.ute.web.models;

import com.ute.web.beans.Auction;
import com.ute.web.beans.BanBidder;
import com.ute.web.utils.DbUtils;
import org.sql2o.Connection;

import java.util.List;

public class BanBidderModel {

    public static List<BanBidder> findAll() {
        final String query = "select * from banbidders";
        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .executeAndFetch(BanBidder.class);
        }
    }
    public static void add(BanBidder b) {
        String Sql = "INSERT INTO banbidders (UserID, ProID) VALUES (:UserID,:ProID)";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(Sql)
                    .addParameter("UserID", b.getUserID())
                    .addParameter("ProID", b.getProID())
                    .executeUpdate();
        }
    }



//    public static void update(Auction a) {
//        String Sql = "UPDATE auctions SET  UserID = :UserID, ProID = :ProID, Price = :Price WHERE AuID = :AuID";
//        try (Connection con = DbUtils.getConnection()) {
//            con.createQuery(Sql)
//                    .addParameter("UserID", a.getUserID())
//                    .addParameter("ProID", a.getProID())
//                    .addParameter("Price", a.getPrice())
//                    .addParameter("AuID" , a.getAuID())
//                    .executeUpdate();
//        }
//    }
//
//    public static int findUserIDProID (int UserID , int ProID) {
//        final String query = "select * from auctions where UserID=:UserID AND ProID=:ProID";
//        try (Connection con = DbUtils.getConnection()) {
//            List<Auction> list = con.createQuery(query)
//                    .addParameter("UserID", UserID)
//                    .addParameter("ProID", ProID)
//                    .executeAndFetch(Auction.class);
//            if (list.size() == 0) {
//                return -1;
//            }
//            return list.get(0).getAuID();
//        }
//    }
//
//
//
//    public static List<Auction> findTopHighestPrice () {
//        final String query = "SELECT * FROM auctions order by Price desc";
//        try (Connection con = DbUtils.getConnection()) {
//            return con.createQuery(query)
//                    .executeAndFetch(Auction.class);
//        }
//    }


}
