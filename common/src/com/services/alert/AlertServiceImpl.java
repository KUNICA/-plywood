package com.services.alert;

import com.entity.Offer;

/**
 * Created by user on 20.08.2016.
 */
public interface AlertServiceImpl {
    void sendAlert(final Offer offer);
    void sendAlertAdmin(final Offer offer);
    void sendAlertAuthorization(final String userName,final String password);
}
