package com.ui;

/**
 * Created by user on 20.08.2016.
 */
public class JspConstants {
    /**
     * Path to app root dir.
     */
    public static final String APP_PATH = "";

    /**
     * Сообщение которое выводится вместо пустого поля в объекте.
     */
    public static final String NO_DATA_MESSAGE = "Сведения отсутствуют";

    static {
        System.out.print(APP_PATH);
        System.out.print(NO_DATA_MESSAGE);
    }
}
