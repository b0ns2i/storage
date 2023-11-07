package com.thewhite.study;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.*;

import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Data {


    UUID id;
    String name;
    String discription;
    String link;

}
