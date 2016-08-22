package com.services;

import com.dao.IObjectInputDao;
import com.dao.objectinput.IObjectTransactInputDao;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
@Named
@Service
public class SaveOrUpdateObjectInputService implements SaveOrUpdateObjectInputServiceImpl {

    @Inject
    private IObjectInputDao saveService;

    @Inject
    protected IObjectTransactInputDao objectInputDao;

    @Override
    public Object inputObject(Object object) {
        return saveService.inputObject(object);
    }

    @Override
    public void inputObject(List objects) {
        objectInputDao.inputObject(objects);
    }

    public IObjectInputDao getSaveService() {
        return saveService;
    }

    public void setSaveService(IObjectInputDao saveService) {
        this.saveService = saveService;
    }



}
