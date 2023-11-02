package com.thewhite.study;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WriterReaderFile {

    String pathFile;

    WriterReaderFile(String pathFile) {
        this.pathFile = pathFile;
    }

    WriterReaderFile(){

    }

    public void write(String json) {
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

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Map<UUID, Data> mapDataItems = gson.fromJson(oldJson, itemsMapType);
        if (mapDataItems == null) {
            mapDataItems = new HashMap<UUID, Data>();
        }

        return mapDataItems;
    }

}
