package com.exel;

import com.entity.Product;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.google.common.base.Strings;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import javax.inject.Named;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by user on 20.08.2016.
 */

public abstract class ExelParser implements ExelParserImpl {

    private static final int NAME_COLUMN_FIELD = 0; //наименование поля
    private static final int DATA_COLUMN_FIELD = 1; //данные поля


    public List getProducts(InputStream inputStream) throws InvalidFormatException, ProductFormatExelExeption {
        XSSFWorkbook workBook = null; // Получаем workbook
        POIFSFileSystem fileSystem = null; //Открываем документу
        List<ProductExel> products = new ArrayList<ProductExel>();
        try {
            //fileSystem = new POIFSFileSystem(inputStream);
            workBook = new XSSFWorkbook(inputStream);

            for (int i = 0; i < workBook.getNumberOfSheets(); i++) {

                XSSFSheet sheet = workBook.getSheetAt(i); // Проверяем страницу

                ProductExel product = getProductExel(sheet,i);

                products.add(product);
            }
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }

    protected ProductExel getProductExel(XSSFSheet sheet,int list_iter) throws ProductFormatExelExeption {
        Iterator<Row> rows = sheet.rowIterator(); // Перебираем все строки
        // Перебираем все строки начиная со второй до тех пор, пока документ не закончитс
        int rowIter = 0;
        ProductExel product = getInstance();
        while (rows.hasNext()) {
            XSSFRow row = (XSSFRow) rows.next();
            //Получаем ячейки из строки по номерам столбцов
            String nameField = getNameField(row);
            String dataField = getDataField(row);

            addField(product,nameField,dataField,list_iter,rowIter);
            rowIter++;
        }
        return product;
    }

    protected String getNameField(XSSFRow row){
        XSSFCell nameFieldXSSFCell = row.getCell(NAME_COLUMN_FIELD);
        return nameFieldXSSFCell.getStringCellValue();
    }

    protected String getDataField(XSSFRow row){
        XSSFCell dataFieldXSSFCell = row.getCell(DATA_COLUMN_FIELD);
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(dataFieldXSSFCell);
    }

    protected abstract void addField(ProductExel product,String nameField,String dataField,int list_iter,int rowIter)throws ProductFormatExelExeption;

    protected abstract ProductExel getInstance();
}
