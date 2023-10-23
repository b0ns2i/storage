package com.thewhite.study;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class MapData {


     Map<UUID, Data> mapDataItems;
     WriterReaderFile wrt;
     Scanner in = new Scanner(System.in);
     MapData(WriterReaderFile wrt){
         this.wrt = wrt;
         mapDataItems = ParserDataStorageJson.ParserStorageJson(wrt);
     }

     public ArrayList<Data> SearchSubnameData(String subname){

                 ArrayList<UUID> uuidslist = new ArrayList<UUID>();
                 ArrayList<Data> listData = new ArrayList<Data>();
                 for(Map.Entry<UUID,Data> entry : mapDataItems.entrySet()){
                     int index =  entry.getValue().name_link.toLowerCase().indexOf(subname);
                     if(index != -1){
                         uuidslist.add(entry.getKey());
                     }
                 }
                 if(uuidslist.size() == 0){
                     return listData;
                 }
                 else{

                     for (int i = 0; i <= uuidslist.size() - 1; i++){
                         listData.add(mapDataItems.get(uuidslist.get(i)));
                     }

                     return listData;

                 }
     }
     public Data GetDataItemID(String uuidStr){
         Data data = new Data();
         try {
             UUID uuidDataItem = UUID.fromString(uuidStr);
             data = mapDataItems.get(uuidDataItem);
             return data;

         }
         catch (IllegalArgumentException ex){
             System.out.println("Неверный формат ID. Попробуйте снова!");
             return data;
         }

     }
     public String AddItemData(Data newData){

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        mapDataItems.put(UUID.randomUUID(), newData);

        return  gson.toJson(mapDataItems);



    }

}
