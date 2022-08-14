package com.ute.web.beans;

public class Category {
    private int CatID , GrCatID;
    private String CatName;

    public Category() {
    }

    public Category(int catID, int grCatID, String catName) {
        CatID = catID;
        GrCatID = grCatID;
        CatName = catName;
    }

    public Category(int grCatID, String catName) {
        CatID = -1;
        GrCatID = grCatID;
        CatName = catName;
    }

    public int getGrCatID() {
        return GrCatID;
    }

    public int getCatID() {
        return CatID;
    }

    public String getCatName() {
        return CatName;
    }

}
