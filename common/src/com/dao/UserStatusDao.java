package com.dao;

import com.entity.UserStatus;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by user on 20.08.2016.
 */
@Component
public class UserStatusDao extends DaoCriteria<Object> implements IUserStatusDao{

    @Autowired
    public UserStatusDao(SessionFactory sessionFactory) {
        super(sessionFactory);      // Конструирует DAO
    }

    @Override
    public UserStatus get(String username) {
        Session session = null;
        Transaction tx = null;
        UserStatus user = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();

            user = (UserStatus)session.get(UserStatus.class, username);

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

    @Override
    public String insert(UserStatus user) {
        Session session = null;
        Transaction tx = null;
        String username = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();

            /*username = (String)*/session.save(user);

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
        return username;
    }

    @Override
    public void update(UserStatus user) {
        Session session = null;
        Transaction tx = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();

            session.saveOrUpdate(user);

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
    }

    @Override
    public void delete(UserStatus user) {
        Session session = null;
        Transaction tx = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();

            session.delete(user);

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
    }


}
