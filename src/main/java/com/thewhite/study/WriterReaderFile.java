package com.thewhite.study;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class WriterReaderFile {

    @Value("${data.path}")
    String pathFile;

    WriterReaderFile(String pathFile) {
        this.pathFile = pathFile;
    }

    WriterReaderFile() {

    }

    public void write(String json) throws Exception {

        if (json == null) {
            throw new Exception("Была переда пустая строка json!");
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathFile))) {

            bw.write(json);
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

    public Map<UUID, Data> readAdnParseJson() {
        String oldJson = "";
        Type itemsMapType = new TypeToken<Map<UUID, Data>>() {
        }.getType();

        try (BufferedReader br = new BufferedReader(new FileReader(pathFile))) {

            String s;
            while ((s = br.readLine()) != null) {

                oldJson += s;
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }

        Map<UUID, Data> mapDataItems = null;
        try {
            mapDataItems = new ObjectMapper().readValue(oldJson, new TypeReference<Map<UUID, Data>>() {
            });
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        if (mapDataItems == null) {
            mapDataItems = new HashMap<UUID, Data>();
        }

        return mapDataItems;
    }

}
