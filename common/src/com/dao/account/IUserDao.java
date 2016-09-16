package com.dao.account;


import com.entity.Users;

/**
 * Created by user on 16.09.2016.
 */
public interface IUserDao {
    Users getUser(String userName);
}
