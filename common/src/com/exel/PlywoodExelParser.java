package com.exel;

import com.google.common.base.Strings;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Component;

import javax.inject.Named;
import java.util.Iterator;

/**
 * Created by user on 23.08.2016.
 */
@Named("plywoodExelParser")
@Component
public class PlywoodExelParser extends ExelParser {

    private enum PlywoodField{

        PRODUCT_ID("Product ID (String)"),
        TYPE("Type"),
        LENGTH("Length (int)"),
        WEIGHT("Weight (int)"),
        THIKNESS("thickness (int)"),
        GRADE("Grade"),
        SANDED("Шлифованный или нет (sanded or unsanded)"),
        WATER_RESISTANCE("Water resistance (FK or -)"),
        PRICE("Price (int)"),
        PHOTO("Фото"),
        AMOUNT_PACKAGE("Количество листов в упаковке  (int)"),
        NUMBER_PACKAGES("Количество упаковок в фуре (22 тонн)  (int)"),
        DESCRIPTION("Описание станка EN /Описание станка  (String)"),
        OTHER("OTHER");

        String nameField;
        PlywoodField(String name){
            this.nameField = name;
        }

        public static PlywoodField getField(int i) {
            PlywoodField[] particleboardFields =  values();
            PlywoodField particleboardField = OTHER;
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
        PlywoodField plywoodField = PlywoodField.getField(rowIter);
        ProductExel product = (ProductExel) obj;
        PlywoodExel plywood = (PlywoodExel) product;
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
            case GRADE:
                if (plywoodField.isStirng(nameField)) {
                    plywood.setGrade(dataField);
                } else {
                    throw new ProductFormatExelExeption(list_iter,plywoodField.getNameField());
                }
                break;
            case WATER_RESISTANCE:
                if (plywoodField.isStirng(nameField)) {
                    plywood.setWaterResistance(dataField);
                } else {
                    throw new ProductFormatExelExeption(list_iter,plywoodField.getNameField());
                }
                break;
            case SANDED:
                if (plywoodField.isStirng(nameField)) {
                    plywood.setSanded(dataField);
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
                // Считываем фото
                if (!Strings.isNullOrEmpty(nameField) && PlywoodField.PHOTO.isStirng(nameField)) {
                    product.getPhotos().put(nameField, dataField);
                }else if(PlywoodField.DESCRIPTION.isStirng(nameField)){
                    product.setShortDescription(dataField);
                }else if(PlywoodField.AMOUNT_PACKAGE.isStirng(nameField)){
                    ((PlywoodExel) product).setAmountPackage(dataField);
                }else if(PlywoodField.NUMBER_PACKAGES.isStirng(nameField)){
                    ((PlywoodExel) product).setNumberPackages(dataField);
                }
                   // throw new ProductFormatExelExeption(list_iter,PlywoodField.OTHER.getNameField());

                break;
        }
    }

    @Override
    protected ProductExel getInstance() {
        return new PlywoodExel();
    }


}
