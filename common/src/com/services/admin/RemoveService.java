package com.services.admin;

import com.entity.*;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;

/**
 * Created by user on 20.08.2016.
 */

@Named
public class RemoveService implements RemoveServiceImpl {

    @Inject
    ImageFileServiceImpl ImageFileService;

    @Override
    public boolean remove(Product product,String userName) {
        for (Images image : product.getPhotos()) {
            image.setOperationOut(getOperationOut(userName));
            ImageFileService.removeFile(image.getImg());
        }
        product.setOperOut(getOperationOut(userName));
        return true;
    }

    private Operations getOperationOut(String userName){
        Operations operationOut = new Operations();
        operationOut.setDateOper(new Date());
        operationOut.setTypeOper(OperationType.OPERATION_OUT);
        operationOut.setUserName(userName);
        return operationOut;
    }
}
