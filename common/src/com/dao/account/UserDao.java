package com.dao.account;

import com.dao.DaoCriteria;
import com.entity.PersonalData;
import com.entity.Users;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

/**
 * Created by user on 16.09.2016.
 */
@Named
public class UserDao  extends DaoCriteria<Users> implements IUserDao {

    @Autowired
    public UserDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Users getUser(String userName){
        Session session = null;
        Transaction tx = null;
        Users user = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();

            user = (Users)createCriteria(session)
                    .add(Restrictions.eq("username",userName))
                    .add(Restrictions.eq("enabled",(byte)1)).uniqueResult();

            tx.commit();
        }
        catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        finally {
            if (session != null && session.isOpen())
                session.close();
        }
        return user;
    }



}
