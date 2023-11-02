package com.thewhite.study;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.UUID;

public class WriterReaderFileTest {
    // Данный тест проверят метод WriterReaderFile.readAndParse() на првильность считывания файла.
    @Test
    void  readAdnParseJsonTest(){
        //Arrange
        String path = "C:\\TheWhite\\BackEnd\\storage\\src\\test\\DB\\DB_links.json";
        WriterReaderFile wrf = new WriterReaderFile(path);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Data expectedData = new Data(
                UUID.fromString("306f52b9-1662-4cd1-b677-0a1a015c693c"),
                "redcom",
                "городской провайдер",
                "redcom.ru");

        //Act
        Map<UUID, Data> DataMap = wrf.readAdnParseJson();
        Data actualData = DataMap.get(expectedData.id);

        //Assert
        Assertions.assertEquals(expectedData.name, actualData.name);
    }

}
