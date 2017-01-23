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
        PRODUCT_ID("Product ID (String)"),
        TYPE("Type"),
        LENGTH("Length (int)"),
        WEIGHT("Weight (int)"),
        THIKNESS("thickness (int)"),
        SANDED("Шлифованное или нет "),
        PRICE("Price"),
        PHOTO("Фото"),
        AMOUNT_PACKAGE("Количество листов в упаковке  (int)"),
        NUMBER_PACKAGES("Количество упаковок в фуре (22 тонн)  (int)"),
        DESCRIPTION("Описание станка EN /Описание станка  (String)"),
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

    protected void addField(Object obj,String nameField,String dataField,int list_iter,int rowIter) throws ProductFormatExelExeption {
        ParticleboardField plywoodField = ParticleboardField.getField(rowIter);
        ProductExel product  =  (ProductExel) obj;
        ParticleboardExel particleboard = (ParticleboardExel) obj;
        switch (plywoodField) {
            case PRODUCT_ID:
                if (plywoodField.isStirng(nameField)) {
                    product.setProductId(dataField);
                }
                break;
            case TYPE:
                if (plywoodField.isStirng(nameField)) {
                    product.setType(dataField);
                }
                break;
            case SANDED:
                if (plywoodField.isStirng(nameField)) {
                    particleboard.setSanded(dataField);
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
                }else if(ParticleboardField.AMOUNT_PACKAGE.isStirng(nameField)){
                    ((ParticleboardExel) product).setAmountPackage(dataField);
                }else if(ParticleboardField.NUMBER_PACKAGES.isStirng(nameField)){
                    ((ParticleboardExel) product).setNumberPackages(dataField);
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
