package com.thewhite.study;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class Data {

    UUID id;
    String name;
    String discription;
    String link;
    Data(){}
    Data(UUID id, String name, String discription, String link){

        this.id = id;
        this.name = name;
        this.discription = discription;
        this.link = link;
    }
}
