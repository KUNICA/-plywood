package com.dao.objectinput;

import javax.inject.Named;
import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
@Named
public class DeclarativeTransactionObjectInputDao implements IObjectTransactInputDao{

    @Override
    public void inputObject(Object object) {
        if (object instanceof List) {
            inputObject((List) object);
        } else {
            // getHibernateTemplate().merge(object);
        }
    }

    public void inputObject(List objects) {
        for (Object listItem : objects) {
            // getHibernateTemplate().merge(listItem);
        }
    }
}
