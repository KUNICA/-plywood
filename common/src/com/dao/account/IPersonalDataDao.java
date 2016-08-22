package com.dao.account;

import com.entity.PersonalData;

/**
 * Created by user on 20.08.2016.
 */
public interface IPersonalDataDao {
    PersonalData getPersonalData(String userName);
}
