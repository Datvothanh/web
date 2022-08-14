package com.ute.web.beans;

public class Auction {
    int AuID, UserID , ProID, Price ;

    public Auction(int auID, int userID, int proID, int price) {
        AuID = auID;
        UserID = userID;
        ProID = proID;
        Price = price;
    }

    public Auction(int userID, int proID, int price) {
        AuID = -1;
        UserID = userID;
        ProID = proID;
        Price = price;
    }

    public int getAuID() {
        return AuID;
    }

    public int getUserID() {
        return UserID;
    }

    public int getProID() {
        return ProID;
    }

    public int getPrice() {
        return Price;
    }
}
