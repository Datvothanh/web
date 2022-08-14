package com.ute.web.beans;

public class GroupCategory {
    int GrCatID ;
    String GrCatName;

    public GroupCategory() {

    }

    public GroupCategory(int grCatID, String grCatName) {
        GrCatID = grCatID;
        GrCatName = grCatName;
    }

    public GroupCategory( String grCatName) {
        GrCatID = -1;
        GrCatName = grCatName;
    }

    public int getGrCatID() {
        return GrCatID;
    }

    public String getGrCatName() {
        return GrCatName;
    }
}
