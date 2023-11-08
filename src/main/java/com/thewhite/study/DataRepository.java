package com.thewhite.study;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class DataRepository {


     Map<UUID, Data> mapDataItems;
     Scanner in = new Scanner(System.in);
     DataRepository(WriterReaderFile wrt){
         mapDataItems = wrt.readAdnParseJson();
     }

     public ArrayList<Data> searchSubnameData(String subname){

         ArrayList<Data> listData = new ArrayList<Data>();

         for(Data data : mapDataItems.values()){

             int index = data.name.toLowerCase().indexOf(subname);
             if(index != -1){
                 listData.add(data);
             }
         }
         return listData;

     }
     public Data getDataItemID(String uuidStr) {
         Data data = new Data();
         try {
             if(uuidStr == null){
                 throw new Exception("Неверный формат ID. Попробуйте снова!");
             }
             UUID uuidDataItem = UUID.fromString(uuidStr);
             data = mapDataItems.get(uuidDataItem);
             return data;

         }
         catch (Exception ex){
             System.out.println("Неверный формат ID. Попробуйте снова!");
             return null;
         }

     }
     public String addItemData(Data newData) throws Exception{

        if(newData == null){
            throw new Exception("Была добавлена пустая запись!");
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        mapDataItems.put(newData.id, newData);

        return  gson.toJson(mapDataItems);
     }

}
