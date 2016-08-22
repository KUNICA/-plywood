package com.services;

import com.dao.IUserStatusDao;
import com.entity.UserStatus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * Created by user on 20.08.2016.
 */
@Service
public class UserStatusService implements IUserStatusService {

    protected static final Logger log = Logger.getLogger(UserStatusService.class);

    /** DAO для UserStatus */
    @Autowired
    protected IUserStatusDao userStatusDao;

    protected Integer allowableFailureCounts = 3;
    protected Integer attemptAuthInterval = 2;
    protected Integer banInterval = 30;


    /**
     * Обрабатывает событие, связанное с неверным входом пользователя
     * @param username String логин пользователя, не прошедшего аутентификацию
     * */
    @Override
    public void processUnsuccessAuthentification(String username) {
        log.debug("on authentification failure");

        UserStatus userStatus = userStatusDao.get(username);

        if(userStatus == null){
            userStatus = new UserStatus(username);
            userStatusDao.insert(userStatus);
        }else{
            // основная логика
            boolean resetStatus = false;
            Calendar calendar = null;

            /* если с момента последней неверной аутентификации прошло attemptAuthInterval часов,
             * счетчик обнуляется, иначе инкрементируется */
            if(userStatus.getLastAuthFailure() != null){
                calendar = Calendar.getInstance();
                calendar.setTime(userStatus.getLastAuthFailure());
                calendar.add(Calendar.HOUR, attemptAuthInterval);
                if(calendar.before(Calendar.getInstance())){
                    resetStatus = true;
                }
            }

            if(resetStatus){
                userStatus.resetStatus();
            }else{
                userStatus.failureIncrement();
            }

            /* если число неверных входов не достигло allowableFailureCounts, просто сохраняем,
             * иначе выставляем бан */
            if(userStatus.getAuthFailureCount() < allowableFailureCounts){
                userStatusDao.update(userStatus);
            }else {
                calendar = Calendar.getInstance();
                log.debug("time before: "+calendar.getTime());
                calendar.add(Calendar.MINUTE, banInterval);
                log.debug("time after: "+calendar.getTime());


                userStatusDao.update(userStatus);
            }
        }
    }

    public Integer getAllowableFailureCounts() {
        return allowableFailureCounts;
    }

    public void setAllowableFailureCounts(Integer allowableFailureCounts) {
        this.allowableFailureCounts = allowableFailureCounts;
    }

    public Integer getAttemptAuthInterval() {
        return attemptAuthInterval;
    }

    public void setAttemptAuthInterval(Integer attemptAuthInterval) {
        this.attemptAuthInterval = attemptAuthInterval;
    }

    public Integer getBanInterval() {
        return banInterval;
    }

    public void setBanInterval(Integer banInterval) {
        this.banInterval = banInterval;
    }
}
