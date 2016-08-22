package com.services.account;

import com.entity.PersonalData;

/**
 * Created by user on 20.08.2016.
 */
public interface IPersonalDataService {
    PersonalData getPersonalData(String userName);
}
