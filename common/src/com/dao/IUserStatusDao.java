package com.dao;

import com.entity.UserStatus;

/**
 * Created by user on 20.08.2016.
 */
public interface IUserStatusDao {

    /**
     * Получение сущности UserStatus
     * @param username String имя пользователя (первичный ключ)
     * @return UserStatus
     * */
    UserStatus get(String username);

    /**
     * Сохранение сущности UserStatus
     * @param user UserStatus сущность
     * @return String имя пользователя (первичный ключ)
     * */
    String insert(UserStatus user);

    /**
     * Обновление сущности UserStatus
     * @param user UserStatus сущность
     * */
    void update(UserStatus user);

    /**
     * Удаление сущности UserStatus
     * @param user UserStatus сущность
     * */
    void delete(UserStatus user);

}
