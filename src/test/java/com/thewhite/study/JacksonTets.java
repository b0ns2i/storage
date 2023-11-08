package com.thewhite.study;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JacksonTets {

    ObjectMapper objectMapper = new ObjectMapper();

    //Проверка работы Jackson
    @Test
    void pojoToJsonString() throws JsonProcessingException {


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

        String json = new ObjectMapper().writeValueAsString(testMap);
        System.out.println(json);
    }


}
