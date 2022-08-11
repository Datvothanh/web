package com.ute.web.beans;

public class Favourite {
    private int FaID , UserID , ProID , Favourite ;

    public Favourite() {
    }

    public Favourite(int faID, int userID, int proID, int favourite) {
        FaID = faID;
        UserID = userID;
        ProID = proID;
        Favourite = favourite;
    }

    public Favourite(int userID, int proID, int favourite) {
        FaID = -1;
        UserID = userID;
        ProID = proID;
        Favourite = favourite;
    }

    public int getFaID() {
        return FaID;
    }

    public int getUserID() {
        return UserID;
    }

    public int getProID() {
        return ProID;
    }

    public int getFavourite() {
        return Favourite;
    }
}
