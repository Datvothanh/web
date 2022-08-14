package com.ute.web.models;

import com.ute.web.beans.Category;
import com.ute.web.beans.GroupCategory;
import com.ute.web.utils.DbUtils;
import org.sql2o.Connection;

import java.util.List;

public class GroupCategoryModel {
    public static List<GroupCategory> findAll() {
        final String query = "select * from groupcategories";
        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .executeAndFetch(GroupCategory.class);
        }
    }

    public static void add(GroupCategory c) {
        String Sql = "INSERT INTO groupcategories (GrCatName ) VALUES (:GrCatName)";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(Sql)
                    .addParameter("GrCatName", c.getGrCatName())
                    .executeUpdate();
        }
    }

    public static GroupCategory findById(int id) {
        final String query = "select * from groupcategories where GrCatID =:GrCatID";
        try (Connection con = DbUtils.getConnection()) {
            List<GroupCategory> list = con.createQuery(query)
                    .addParameter("GrCatID", id)
                    .executeAndFetch(GroupCategory.class);
            if (list.size() == 0) {
                return null;
            }
            return list.get(0);
        }
    }

    public static void update(GroupCategory c) {
        String Sql = "UPDATE groupcategories set GrCatName = :GrCatName where GrCatID = :GrCatID";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(Sql)
                    .addParameter("GrCatName", c.getGrCatName())
                    .addParameter("GrCatID", c.getGrCatID())
                    .executeUpdate();
        }
    }

    public static void delete(int id) {
        String Sql = "DELETE from groupcategories where GrCatID = :GrCatID";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(Sql)
                    .addParameter("GrCatID", id)
                    .executeUpdate();
        }
    }
}
