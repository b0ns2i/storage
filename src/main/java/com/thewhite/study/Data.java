package com.thewhite.study;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class Data {

    String name_link;
    String discription_link;
    String link;
    Data(){}
    Data(String name_link, String discription_link, String link){

        this.name_link = name_link;
        this.discription_link = discription_link;
        this.link = link;
    }
}
