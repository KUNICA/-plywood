package com.dao;

import org.springframework.util.ReflectionUtils ;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by user on 31.08.2016.
 */
public class DaoCriteria<T> {

    private SessionFactory sessionFactory;
    private Class<T> persistentClass;

    @Autowired
    @SuppressWarnings("unchecked")
    public DaoCriteria(SessionFactory sessionFactory) {

        Type genericSuperClass = getClass().getGenericSuperclass();

        ParameterizedType parametrizedType = null;
        while (parametrizedType == null) {
            if ((genericSuperClass instanceof ParameterizedType)) {
                parametrizedType = (ParameterizedType) genericSuperClass;
            } else {
                genericSuperClass = ((Class<?>) genericSuperClass).getGenericSuperclass();
            }
        }

        this.persistentClass = (Class<T>) parametrizedType.getActualTypeArguments()[0];
        this.sessionFactory = sessionFactory;      // Конструирует DAO
    }

    protected Session currentSession() {           // Извлекает текущий
        return sessionFactory.getCurrentSession(); // сеанс из фабрики
    }
    protected Criteria createCriteria(Session session){
       return session.createCriteria(persistentClass);
    }

}
