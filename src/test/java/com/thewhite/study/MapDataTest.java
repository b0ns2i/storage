package com.thewhite.study;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.accessibility.Accessible;
import java.util.ArrayList;
import java.util.UUID;

public class MapDataTest {

    @Test
    void GetDataItemIDExceptionTest(){
        //Данный тест проверяет верно ли метод MapData.GetDataItemID(String uuid) обрабатывает неверный формат ID

        WriterReaderFile wrf = new WriterReaderFile("C:\\TheWhite\\BackEnd\\" +
                                                                "storage\\src\\test\\DB\\DB_links.json");
        MapData mapData = new MapData(wrf);
        String uuid = "мой неправильный ID";

        Data dataExpected = mapData.GetDataItemID(uuid);

        Assertions.assertNull(dataExpected.link);

    }

    @Test
    void GetDataItemIDFoundTest(){
        //Данный тест проверяет находит ли метод MapData.GetDataItemID(String uuid) нужную запись.
        WriterReaderFile wrf = new WriterReaderFile("C:\\TheWhite\\BackEnd\\" +
                "storage\\src\\test\\DB\\DB_links.json");
        MapData mapData = new MapData(wrf);
        String uuid = "306f52b9-1662-4cd1-b677-0a1a015c693c";

        Data dataExpected = mapData.GetDataItemID(uuid);

        Assertions.assertEquals(dataExpected.link, "redcom.ru");

    }

    @Test
    void SearchSubnameDataTes(){
        //Данный тест проверяет обработку метода MapData.SearchSubnameData(string subname) при неверный параметре поиска
        WriterReaderFile wrf = new WriterReaderFile("C:\\TheWhite\\BackEnd\\" +
                "storage\\src\\test\\DB\\DB_links.json");
        MapData mapData = new MapData(wrf);
        String subname = "Неверный параметр поиска!";

        ArrayList<Data> listData = mapData.SearchSubnameData(subname);

        Assertions.assertEquals(listData.size(), 0);
    }





}
