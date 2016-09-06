package com.exel;

import com.entity.Particleboard;
import com.entity.Product;
import com.google.common.base.Strings;
import org.springframework.stereotype.Component;

import javax.inject.Named;

/**
 * Created by user on 23.08.2016.
 */
@Named("particleboardExelParser")
@Component
public class ParticleboardExelParser extends ExelParser {

    private enum ParticleboardField{
        PRODUCT_ID("Product ID"),
        COATING("Coating"),
        GRADE("Grade"),
        LAMINATED("Laminated"),
        THIKNESS("Thickness"),
        LENGTH("Length"),
        WEIGHT("Weight"),
        PRICE("Price"),
        PHOTO("Photo"),
        DESCRIPTION("Description"),
        OTHER("OTHER");

        String nameField;
        ParticleboardField(String name){
            this.nameField = name;
        }

        public static ParticleboardField getField(int i) {
            ParticleboardField[] particleboardFields =  values();
            ParticleboardField particleboardField = OTHER;
            return (i<particleboardFields.length ? particleboardFields[i]: OTHER);
        }

        public String getNameField() {
            return nameField;
        }

        boolean isStirng(String ch){
            return !Strings.isNullOrEmpty(ch) && ch.indexOf(this.nameField)!=-1;
        }
    }

    protected void addField(ProductExel product,String nameField,String dataField,int list_iter,int rowIter) throws ProductFormatExelExeption {
        ParticleboardField plywoodField = ParticleboardField.getField(rowIter);
        ParticleboardExel particleboard = (ParticleboardExel) product;
        switch (plywoodField) {
            case PRODUCT_ID:
                if (plywoodField.isStirng(nameField)) {
                    product.setProductId(dataField);
                }
                break;
            case COATING:
                if (plywoodField.isStirng(nameField)) {
                    particleboard.setCoating(dataField);
                } else {
                    throw new ProductFormatExelExeption(list_iter,plywoodField.getNameField());
                }
                break;
            case GRADE:
                if (plywoodField.isStirng(nameField)) {
                    particleboard.setGrade(dataField);
                } else {
                    throw new ProductFormatExelExeption(list_iter,plywoodField.getNameField());
                }
                break;
            case LAMINATED:
                if (plywoodField.isStirng(nameField)) {
                    particleboard.setLaminated(dataField);
                } else {
                    throw new ProductFormatExelExeption(list_iter,plywoodField.getNameField());
                }
                break;
            case THIKNESS:
                if (plywoodField.isStirng(nameField)) {
                    product.setThickness(dataField);
                } else {
                    throw new ProductFormatExelExeption(list_iter,plywoodField.getNameField());
                }
                break;
            case LENGTH:
                if (plywoodField.isStirng(nameField)) {
                    product.setLength(dataField);
                } else {
                    throw new ProductFormatExelExeption(list_iter,plywoodField.getNameField());
                }
                break;
            case WEIGHT:
                if (plywoodField.isStirng(nameField)) {
                    product.setWeight(dataField);
                } else {
                    throw new ProductFormatExelExeption(list_iter,plywoodField.getNameField());
                }
                break;
            case PRICE:
                if (plywoodField.isStirng(nameField)) {
                    product.setPrice(dataField);
                } else {
                    throw new ProductFormatExelExeption(list_iter,plywoodField.getNameField());
                }
                break;
            default:
                // Считываем фото и видио
                if (!Strings.isNullOrEmpty(nameField) && ParticleboardField.PHOTO.isStirng(nameField)) {
                    product.getPhotos().put(nameField, dataField);
                }else if(ParticleboardField.DESCRIPTION.isStirng(nameField)){
                    product.setShortDescription(dataField);
                }
                    //throw new ProductFormatExelExeption(list_iter, ParticleboardField.OTHER.getNameField());
                break;
        }
    }

    @Override
    protected ProductExel getInstance() {
        return new ParticleboardExel();
    }
}
