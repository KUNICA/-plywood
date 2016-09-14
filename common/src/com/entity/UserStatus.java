package com.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by user on 20.08.2016.
 */
@Entity
@Table(name = "user_status")
public class UserStatus {
    private String username;
    private Integer authFailureCount;
    private Date lastAuthFailure;

    public UserStatus() {
    }

    @Id
    @Column(name = "username", nullable = false, length = 255)
    public String getUsername() {
        return username;
    }

    public UserStatus(String username) {
        this.username = username;
        this.authFailureCount = 1;
        this.lastAuthFailure =  new Date();
    }

    public UserStatus(String username, int authFailureCount, java.util.Date lastAuthFailure) {
        this.username = username;
        this.authFailureCount = authFailureCount;
        this.lastAuthFailure = lastAuthFailure;
    }

    /**
     * Инкрементирует неудачные попытки авторизации
     * */
    public void failureIncrement(){
        this.authFailureCount++;
        this.lastAuthFailure = new java.util.Date();
    }

    /**
     * Сброс статуса
     * */
    public void resetStatus(){
        this.authFailureCount = 1;
        this.lastAuthFailure = new java.util.Date();
    }

    public int compareTo(UserStatus o) {
        String value = (username!=null ? username : "");
        String anotherValue = "";
        if(o!=null && o.getUsername()!=null){
            anotherValue = o.getUsername();
        }
        return value.compareTo(anotherValue);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "auth_failure_count", nullable = true)
    public Integer getAuthFailureCount() {
        return authFailureCount;
    }

    public void setAuthFailureCount(Integer authFailureCount) {
        this.authFailureCount = authFailureCount;
    }

    @Basic
    @Column(name = "last_auth_failure", nullable = true)
    public Date getLastAuthFailure() {
        return lastAuthFailure;
    }

    public void setLastAuthFailure(Date lastAuthFailure) {
        this.lastAuthFailure = lastAuthFailure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserStatus that = (UserStatus) o;

        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (authFailureCount != null ? !authFailureCount.equals(that.authFailureCount) : that.authFailureCount != null)
            return false;
        if (lastAuthFailure != null ? !lastAuthFailure.equals(that.lastAuthFailure) : that.lastAuthFailure != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (authFailureCount != null ? authFailureCount.hashCode() : 0);
        result = 31 * result + (lastAuthFailure != null ? lastAuthFailure.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserStatus{" +
                "username='" + username + '\'' +
                ", authFailureCount=" + authFailureCount +
                ", lastAuthFailure=" + lastAuthFailure +
                '}';
    }
}
