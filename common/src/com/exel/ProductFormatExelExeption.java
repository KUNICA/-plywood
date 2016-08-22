package com.exel;

/**
 * Created by user on 20.08.2016.
 */
public class ProductFormatExelExeption extends Exception {
    private String fildName = "Ошибка! Не правильно сформированы данные в exel поле:";
    private int sheet;

    public ProductFormatExelExeption(int sheet, String fildName) {
        this.fildName += fildName + " страница:" + sheet;
    }

    public String getFildName() {
        return fildName;
    }
}
