package com.ute.web.beans;

public class BanBidder {
    int BaID , UserID , ProID;

    public BanBidder(int baID, int userID, int proID) {
        BaID = baID;
        UserID = userID;
        ProID = proID;
    }

    public BanBidder(int userID, int proID) {
        BaID = -1;
        UserID = userID;
        ProID = proID;
    }

    public int getBaID() {
        return BaID;
    }

    public int getUserID() {
        return UserID;
    }

    public int getProID() {
        return ProID;
    }
}
