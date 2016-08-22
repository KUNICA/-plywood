package com.dao.product;

import com.entity.Product;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Named;
import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
@Named
@Component
public class ProductDao implements ProductDaoImpl {

    private SessionFactory sessionFactory;

    private Criteria getCriteriaProducts(Session session){
        session.enableFetchProfile("mediaProfile");
        return session.createCriteria(Product.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .add(Restrictions.isNull("operationOut"));
    }

    @Autowired
    public ProductDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;      // Конструирует DAO
    }

    private Session currentSession() {           // Извлекает текущий
        return sessionFactory.getCurrentSession(); // сеанс из фабрики
    }


    @Override
    public List getProducts(List productsIdList) {
        return null;
    }

    @Override
    public Object getProduct(Long productsId) {
        return null;
    }

    @Override
    public List getProducts(String userName) {
        return null;
    }
}
