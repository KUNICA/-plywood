package com.exel;

/**
 * Created by user on 20.08.2016.
 */
public class ProductFormatExelExeption extends Exception {
    private String fildName = "Error! Malformed data in excel field:";
    private int sheet;

    public ProductFormatExelExeption(int sheet, String fildName) {
        this.fildName += fildName + " page:" + sheet;
    }

    public String getFildName() {
        return fildName;
    }
}
