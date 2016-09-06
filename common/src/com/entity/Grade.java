package com.entity;

import com.google.common.base.Strings;

/**
 * Created by user on 05.09.2016.
 */
public enum Grade {
    FIRST("first");
    String nameField;
    Grade(String name){
        this.nameField = name;
    }

    public String getNameField() {
        return nameField;
    }

    public boolean isStirng(String ch){
        return !Strings.isNullOrEmpty(ch) && ch.indexOf(this.nameField)!=-1;
    }

    public static Grade getField(String ch) {
        Grade[] particleboardFields =  values();
        Grade particleboardField = null;
        for(int i=0; i<particleboardFields.length;i++){
            if(particleboardFields[i].isStirng(ch)){
                particleboardField = particleboardFields[i];
            }
        }
        return particleboardField;
    }

    public static boolean isField(String ch) {
        Grade[] particleboardFields =  values();
        boolean particleboardField = false;
        for(int i=0; i<particleboardFields.length;i++){
            if(particleboardFields[i].isStirng(ch)){
                particleboardField = true;
            }
        }
        return particleboardField;
    }

}
