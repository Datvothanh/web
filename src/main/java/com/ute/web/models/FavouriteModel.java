package com.ute.web.models;

import com.ute.web.beans.Favourite;
import com.ute.web.utils.DbUtils;
import org.sql2o.Connection;

import java.util.List;

public class FavouriteModel {

    public static List<Favourite> findAll() {
        final String query = "select * from favourites";
        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .executeAndFetch(Favourite.class);
        }
    }
    public static void add(Favourite f) {
        String Sql = "INSERT INTO favourites (UserID, ProID, Favourite) VALUES (:UserID,:ProID,:Favourite)";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(Sql)
                    .addParameter("UserID", f.getUserID())
                    .addParameter("ProID", f.getProID())
                    .addParameter("Favourite", f.getFavourite())
                    .executeUpdate();
        }
    }

    public static void update(Favourite f) {
        String Sql = "UPDATE favourites SET  UserID = :UserID, ProID = :ProID, Favourite = :Favourite WHERE FaID = :FaID";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(Sql)
                    .addParameter("UserID", f.getUserID())
                    .addParameter("ProID", f.getProID())
                    .addParameter("Favourite", f.getFavourite())
                    .addParameter("FaID" , f.getFaID())
                    .executeUpdate();
        }
    }

}
