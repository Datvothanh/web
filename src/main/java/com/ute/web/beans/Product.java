package com.ute.web.beans;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Product {
    private int ProID, StartingPrice, CatID, StepPrice, HighestPaidPrice, NowPrice, AutoExtend , UserID ,Sell;
    private String ProName, TinyDes, FullDes;
    private LocalDate StartDay, EndDay;

    public Product() {

    }

    public Product(int proID, int highestPaidPrice, int userID) {
        ProID = proID;
        HighestPaidPrice = highestPaidPrice;
        UserID = userID;
    }

    public Product(int proID, int startingPrice, int catID, int stepPrice, int highestPaidPrice, int nowPrice, int autoExtend, String proName, String tinyDes, String fullDes, LocalDate startDay, LocalDate endDay) {
        ProID = proID;
        StartingPrice = startingPrice;
        CatID = catID;
        StepPrice = stepPrice;
        HighestPaidPrice = highestPaidPrice;
        NowPrice = nowPrice;
        AutoExtend = autoExtend;
        ProName = proName;
        TinyDes = tinyDes;
        FullDes = fullDes;
        StartDay = startDay;
        EndDay = endDay;
    }

    public Product(int startingPrice, int catID, int stepPrice, int highestPaidPrice, int nowPrice, int autoExtend, int userID, int sell, String proName, String tinyDes, String fullDes, LocalDate startDay, LocalDate endDay) {
        ProID = -1;
        StartingPrice = startingPrice;
        CatID = catID;
        StepPrice = stepPrice;
        HighestPaidPrice = highestPaidPrice;
        NowPrice = nowPrice;
        AutoExtend = autoExtend;
        UserID = userID;
        Sell = sell;
        ProName = proName;
        TinyDes = tinyDes;
        FullDes = fullDes;
        StartDay = startDay;
        EndDay = endDay;
    }



    public int getProID() {
        return ProID;
    }

    public int getStartingPrice() {
        return StartingPrice;
    }

    public int getCatID() {
        return CatID;
    }

    public int getStepPrice() {
        return StepPrice;
    }

    public int getHighestPaidPrice() {
        return HighestPaidPrice;
    }

    public int getNowPrice() {
        return NowPrice;
    }

    public int getAutoExtend() {
        return AutoExtend;
    }

    public String getProName() {
        return ProName;
    }

    public String getTinyDes() {
        return TinyDes;
    }

    public String getFullDes() {
        return FullDes;
    }

    public LocalDate getStartDay() {
        return StartDay;
    }

    public LocalDate getEndDay() {
        return EndDay;
    }

    public int getUserID() {
        return UserID;
    }

    public int getSell() {
        return Sell;
    }
}

