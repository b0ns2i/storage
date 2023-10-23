package com.thewhite.study;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WriterReaderFileTest {

    @Test
    void  ReaderWRFTest(){
        // Данный тест проверят матод WriterReaderFile.ReaderWRT на првильность считывания файла.
        String path = "C:\\TheWhite\\BackEnd\\storage\\src\\test\\DB\\DB_links.json";
        WriterReaderFile wrf = new WriterReaderFile(path);
        String actualData = "{"+
                "  \"306f52b9-1662-4cd1-b677-0a1a015c693c\": {"+
                "    \"name_link\": \"redcom\","+
                "    \"discription_link\": \"городской провайдер\","+
                "    \"link\": \"redcom.ru\"  "+
        "},"+
                "  \"e582a685-5500-45ed-91a5-70e88d0a21cf\": {"+
                "    \"name_link\": \"metanit \","+
                "    \"discription_link\": \"site metanit \","+
                "    \"link\": \"metanit.com \"  "+
                "},"+
                "  \"745b9ceb-dc73-4bdd-8065-71a986a984c2\": {"+
                "    \"name_link\": \"CaLReD\","+
                "    \"discription_link\": \"asdagdsaqsd\","+
                "    \"link\": \"REDCAL.COM\"  "+
                "},"+
                "  \"7fc79a46-952d-4526-9327-217aa8b6083a\": {"+
                "    \"name_link\": \"ReDCoM\","+
                "    \"discription_link\": \"ReDCOM site\","+
                "    \"link\": \"ReDCOM.ru\"  "+
                "}"+
                "}";


        String  expectedData = wrf.ReaderWRF();


        Assertions.assertEquals(expectedData,actualData);
    }

}
