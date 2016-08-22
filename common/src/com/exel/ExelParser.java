package com.exel;

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
@Named
@Component
public class ExelParser implements ExelParserImpl {

    private static final int ID_ROW_NUMBER = 0; //Наименование
    private static final int NAME_ROW_NUMBER = 1; //Наименование
    private static final int ADDRESS_ROW_CITY = 2; //Город
    private static final int STREET_ROW_NUMBER = 3; //Улица
    private static final int HOUSE_ROW_NUMBER = 4; //дом
    private static final int HOUSING_ROW_NUMBER = 5; //корпус
    private static final int APARTMENT_ROW_NUMBER = 6; //квартира
    private static final int SLEEPS_ROW_NUMBER = 7; //колличество персон
    private static final int COUNT_ROOMS_ROW_NUMBER = 8; //колличество комнат
    private static final int COUNT_FLOORS_ROW_NUMBER = 9; //колличество этажей
    private static final int COUNT_BATHROOMS_ROW_NUMBER = 10; //колличество ванных комнат
    private static final int PRICE_ROW_NUMBER = 11; //цена
    private static final int DESCRIPTION_ROW_NUMBER = 12; //описание

    private static final String ID_ROW_NUMBER_HEADER = "Идентификатор"; //Наименование
    private static final String NAME_ROW_NUMBER_HEADER = "Наименование"; //Наименование
    private static final String ADDRESS_ROW_CITY_HEADER = "Город"; //Город
    private static final String STREET_ROW_NUMBER_HEADER = "Улица"; //Улица
    private static final String HOUSE_ROW_NUMBER_HEADER = "Дом"; //дом
    private static final String HOUSING_ROW_NUMBER_HEADER = "Корпус"; //корпус
    private static final String APARTMENT_ROW_NUMBER_HEADER = "Квартира"; //квартира
    private static final String SLEEPS_ROW_NUMBER_HEADER = "Колличество людей"; //колличество персон
    private static final String COUNT_ROOMS_ROW_NUMBER_HEADER = "Колличество комнат"; //колличество комнат
    private static final String COUNT_FLOORS_ROW_NUMBER_HEADER = "Колличество этажей"; //колличество этажей
    private static final String COUNT_BATHROOMS_ROW_NUMBER_HEADER = "Колличество ванных комнат"; //колличество ванных комнат
    private static final String PRICE_ROW_NUMBER_HEADER = "Цена"; //цена
    private static final String DESCRIPTION_ROW_NUMBER_HEADER = "Описание"; //описание


    private static final String PHOTO_ROW = "photo"; //фотографии
    private static final String VIDEO_ROW = "video"; //видео

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

                ProductExel product = new ProductExel();

                XSSFSheet sheet = workBook.getSheetAt(i); // Проверяем страницу

                Iterator<Row> rows = sheet.rowIterator(); // Перебираем все строки

                // Перебираем все строки начиная со второй до тех пор, пока документ не закончитс
                int rowIter = 0;
                while (rows.hasNext()) {
                    XSSFRow row = (XSSFRow) rows.next();
                    //Получаем ячейки из строки по номерам столбцов
                    XSSFCell nameFieldXSSFCell = row.getCell(NAME_COLUMN_FIELD);
                    String nameField = nameFieldXSSFCell.getStringCellValue();
                    XSSFCell dataFieldXSSFCell = row.getCell(DATA_COLUMN_FIELD);
                    DataFormatter formatter = new DataFormatter();
                    String dataField = formatter.formatCellValue(dataFieldXSSFCell);

                    switch (rowIter) {
                        case ID_ROW_NUMBER:
                            if (!Strings.isNullOrEmpty(nameField) && nameField.contains(ID_ROW_NUMBER_HEADER)) {
                                product.setId(dataField);
                            }
                            break;
                        case NAME_ROW_NUMBER:
                            if (!Strings.isNullOrEmpty(nameField) && nameField.contains(NAME_ROW_NUMBER_HEADER)) {
                                product.setName(dataField);
                            } else {
                                throw new ProductFormatExelExeption(i, NAME_ROW_NUMBER_HEADER);
                            }
                            break;
                        case ADDRESS_ROW_CITY:
                            if (!Strings.isNullOrEmpty(nameField) && nameField.contains(ADDRESS_ROW_CITY_HEADER)) {
                                product.setCity(dataField);
                            } else {
                                throw new ProductFormatExelExeption(i, ADDRESS_ROW_CITY_HEADER);
                            }
                            break;
                        case STREET_ROW_NUMBER:
                            if (!Strings.isNullOrEmpty(nameField) && nameField.contains(STREET_ROW_NUMBER_HEADER)) {
                                product.setStreet(dataField);
                            } else {
                                throw new ProductFormatExelExeption(i, STREET_ROW_NUMBER_HEADER);
                            }
                            break;
                        case HOUSE_ROW_NUMBER:
                            if (!Strings.isNullOrEmpty(nameField) && nameField.contains(HOUSE_ROW_NUMBER_HEADER)) {
                                product.setHouse(dataField);
                            } else {
                                throw new ProductFormatExelExeption(i, HOUSE_ROW_NUMBER_HEADER);
                            }
                            break;
                        case HOUSING_ROW_NUMBER:
                            if (!Strings.isNullOrEmpty(nameField) && nameField.contains(HOUSING_ROW_NUMBER_HEADER)) {
                                product.setHousing(dataField);
                            } else {
                                throw new ProductFormatExelExeption(i, HOUSING_ROW_NUMBER_HEADER);
                            }
                            break;
                        case APARTMENT_ROW_NUMBER:
                            if (!Strings.isNullOrEmpty(nameField) && nameField.contains(APARTMENT_ROW_NUMBER_HEADER)) {
                                product.setApartment(dataField);
                            } else {
                                throw new ProductFormatExelExeption(i, APARTMENT_ROW_NUMBER_HEADER);
                            }
                            break;
                        case SLEEPS_ROW_NUMBER:
                            if (!Strings.isNullOrEmpty(nameField) && nameField.contains(SLEEPS_ROW_NUMBER_HEADER)) {
                                product.setSleeps(dataField);
                            } else {
                                throw new ProductFormatExelExeption(i, SLEEPS_ROW_NUMBER_HEADER);
                            }
                            break;
                        case COUNT_ROOMS_ROW_NUMBER:
                            if (!Strings.isNullOrEmpty(nameField) && nameField.contains(COUNT_ROOMS_ROW_NUMBER_HEADER)) {
                                product.setCountRooms(dataField);
                            } else {
                                throw new ProductFormatExelExeption(i, COUNT_ROOMS_ROW_NUMBER_HEADER);
                            }
                            break;
                        case COUNT_FLOORS_ROW_NUMBER:
                            if (!Strings.isNullOrEmpty(nameField) && nameField.contains(COUNT_FLOORS_ROW_NUMBER_HEADER)) {
                                product.setCountFloors(dataField);
                            } else {
                                throw new ProductFormatExelExeption(i, COUNT_FLOORS_ROW_NUMBER_HEADER);
                            }
                            break;
                        case COUNT_BATHROOMS_ROW_NUMBER:
                            if (!Strings.isNullOrEmpty(nameField) && nameField.contains(COUNT_BATHROOMS_ROW_NUMBER_HEADER)) {
                                product.setCountBathrooms(dataField);
                            } else {
                                throw new ProductFormatExelExeption(i, COUNT_BATHROOMS_ROW_NUMBER_HEADER);
                            }
                            break;
                        case PRICE_ROW_NUMBER:
                            if (!Strings.isNullOrEmpty(nameField) && nameField.contains(PRICE_ROW_NUMBER_HEADER)) {
                                product.setPrice(dataField);
                            } else {
                                throw new ProductFormatExelExeption(i, PRICE_ROW_NUMBER_HEADER);
                            }
                            break;
                        case DESCRIPTION_ROW_NUMBER:
                            if (!Strings.isNullOrEmpty(nameField) && nameField.contains(DESCRIPTION_ROW_NUMBER_HEADER)) {
                                product.setShortDescription(dataField);
                            } else {
                                throw new ProductFormatExelExeption(i, DESCRIPTION_ROW_NUMBER_HEADER);
                            }
                            break;
                        default:
                            // Считываем фото и видио
                            if (!Strings.isNullOrEmpty(nameField) && nameField.contains(PHOTO_ROW)) {
                                product.getPhotos().put(nameField, dataField);
                            } else if (nameField.contains(VIDEO_ROW)) {
                                product.getVideos().put(nameField, dataField);
                            }

                            break;
                    }
                    rowIter++;
                }
                products.add(product);
            }
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }
}
