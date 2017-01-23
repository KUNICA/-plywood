package com.services.alert;

import com.dataweb.LeaveMessageValid;
import com.entity.Mailing;
import com.entity.Offer;

/**
 * Created by user on 20.08.2016.
 */
public interface AlertServiceImpl {
    void sendAlert(final Offer offer);
    void sendAlertAdmin(final Offer offer);
    void sendAlertAuthorization(final String userName,final String password);
    void sendAlertMessageContacts(final LeaveMessageValid leaveMessageValid);
    void sendAlertMessageCompanyOffer(final Mailing mailing, final String name,String logo,final String signature,final String toEmail);
}
