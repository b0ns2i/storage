package com.thewhite.study;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DataRepositoryTest {

    //Данный тест проверяет верно ли метод MapData.GetDataItemID(String uuid) обрабатывает неверный формат ID
    @Test
    void getDataItemIDExceptionTest() throws Exception {

        //Arrange
        Map<UUID, Data> testMap = new HashMap<UUID, Data>();
        Data testData1 = new Data(
                UUID.fromString("306f52b9-1662-4cd1-b677-0a1a015c693c"),
                "redcom",
                "городской провайдер",
                "redcom.ru");
        Data testData2 = new Data(
                UUID.fromString("e582a685-5500-45ed-91a5-70e88d0a21cf"),
                "metanit",
                "site metanit",
                "metanit.com");

        testMap.put(testData1.id, testData1);
        testMap.put(testData2.id, testData2);
        WriterReaderFile wrf = Mockito.mock(WriterReaderFile.class);
        when(wrf.readAdnParseJson()).thenReturn(testMap);
        DataRepository dataRepository = new DataRepository(wrf);
        String uuid = null;
        Data dataExpected = new Data();
        //Act

        try{
             dataExpected = dataRepository.getDataItemID(uuid);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        //Assert
        Assertions.assertNull(dataExpected);

    }

    //Данный тест проверяет находит ли метод MapData.GetDataItemID(String uuid) нужную запись.
    @Test
    void getDataItemIDFoundTest() {

        //Arrange
        Map<UUID, Data> testMap = new HashMap<UUID, Data>();
        Data testData1 = new Data(
                UUID.fromString("306f52b9-1662-4cd1-b677-0a1a015c693c"),
                "redcom",
                "городской провайдер",
                "redcom.ru");
        testMap.put(testData1.id, testData1);
        WriterReaderFile wrf = Mockito.mock(WriterReaderFile.class);
        when(wrf.readAdnParseJson()).thenReturn(testMap);
        DataRepository dataRepository = new DataRepository(wrf);
        String uuid = "306f52b9-1662-4cd1-b677-0a1a015c693c";
        Data dataExpected = new Data();
        //Act
        try {
            dataExpected = dataRepository.getDataItemID(uuid);
        }
        catch (Exception ex){

        }


        //Assert
        Assertions.assertEquals(dataExpected.link, "redcom.ru");

    }

    //Данный тест проверяет обработку метода MapData.SearchSubnameData(string subname) при неверный параметре поиска
    @Test
    void earchSubnameDataTes() {

        //Arrange
        Map<UUID, Data> testMap = new HashMap<UUID, Data>();
        Data testData1 = new Data(
                UUID.fromString("306f52b9-1662-4cd1-b677-0a1a015c693c"),
                "redcom",
                "городской провайдер",
                "redcom.ru");
        testMap.put(testData1.id, testData1);
        WriterReaderFile wrf = Mockito.mock(WriterReaderFile.class);
        when(wrf.readAdnParseJson()).thenReturn(testMap);
        DataRepository dataRepository = new DataRepository(wrf);
        String subname = "Неверный параметр поиска!";

        //Act
        ArrayList<Data> listData = dataRepository.searchSubnameData(subname);

        //Assert
        Assertions.assertEquals(listData.size(), 0);
    }

    //Данный тест проверяет правильность вывода ошибки при вызове метода dataRepository.addItemData(null)
    @Test
    void addItemDataTestException(){
        //Arrange
        Map<UUID, Data> testMap = new HashMap<UUID, Data>();
        WriterReaderFile wrf = Mockito.mock(WriterReaderFile.class);
        when(wrf.readAdnParseJson()).thenReturn(testMap);
        DataRepository dataRepository = new DataRepository(wrf);
        String actualException = "Была добавлена пустая запись!";
        String expactidException = "";
        //Act

        try {
            dataRepository.addItemData(null);
        }
        catch (Exception ex){
            expactidException = ex.getMessage();
        }

        //Assert
        Assertions.assertEquals(expactidException,actualException);
    }


}
