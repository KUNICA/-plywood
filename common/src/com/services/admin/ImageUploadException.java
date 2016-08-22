package com.services.admin;

/**
 * Created by user on 20.08.2016.
 */
public class ImageUploadException extends Exception {
    String except;
    public ImageUploadException(String except){
        this.except = except;
    }
}
