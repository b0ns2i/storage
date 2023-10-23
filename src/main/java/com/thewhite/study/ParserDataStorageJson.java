package com.thewhite.study;
import java.util.*;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


public class ParserDataStorageJson {

    static Map<UUID, Data> ParserStorageJson(WriterReaderFile wrt){
        Scanner in = new Scanner(System.in);

        Type itemsMapType = new TypeToken<Map<UUID, Data>>() {}.getType();

        String jsonOld = wrt.ReaderWRF();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Map<UUID, Data> mapDataItems = gson.fromJson(jsonOld, itemsMapType);
        if(mapDataItems == null){
            mapDataItems = new HashMap<UUID, Data>();
        }

        return mapDataItems;
    }

}
