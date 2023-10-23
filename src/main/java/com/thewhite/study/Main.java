package com.thewhite.study;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;


public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Введи путь до файла хранения данных:");
        String pathJson = in.nextLine();
        WriterReaderFile wrf = new WriterReaderFile(pathJson);
        MapData mapData = new MapData(wrf);

        boolean check = true;

        while (check) {


            System.out.println(
                    "Выберите один из пунктов меню:\n" +
                            "\t1. Получить запись по идентификатору;\n" +
                            "\t2. Найти запись по части наименования (без учёта регистра);\n" +
                            "\t3. Дабавить новую запись;\n" +
                            "\t4. Закрыть;\n"

            );

            String itemMenu = in.nextLine();

            switch (itemMenu) {
                case "1":

                    System.out.println("Введите ID записи. Нажмите \":q\" для выхода в главное меню.");
                    boolean checkGTIID = true;
                    while (checkGTIID) {
                        String uuidStr = in.nextLine();
                        if (uuidStr.equals(":q")) {
                            checkGTIID = false;
                        } else {

                            Data data = mapData.GetDataItemID(uuidStr);

                            if (data == null) {
                                System.out.println("Запись не найдена!");
                            } else {
                                System.out.printf(" Название записис: %s;\n Описание записи: %s;\n Ссылка: %s;\n",
                                        data.name_link,
                                        data.discription_link,
                                        data.link);
                                System.out.println("---------------------------------");
                                System.out.println("Введите ID или \":q\" для выхода");

                            }
                        }
                    }
                    break;
                case "2":


                    boolean checkSSD = true;
                    while (checkSSD) {
                        System.out.println("Введите имя/часть имени записи. Нажмите \":q\" для выхода в главное меню.");
                        String subname = in.nextLine();
                        if (subname.equals(":q")){
                            checkSSD = false;
                        }
                        else{
                            if(subname.equals("")){
                                System.out.println("Некорректная подстрока");
                            }
                            else{
                                ArrayList<Data> listData = mapData.SearchSubnameData(subname.toLowerCase());
                                if(listData.size() == 0){
                                    System.out.println("Не удалось найти запись");
                                }
                                else{
                                    for(Data data : listData){
                                        System.out.printf(" Название записис: %s;\n Описание записи: %s;\n Ссылка: %s;\n",
                                                data.name_link,
                                                data.discription_link,
                                                data.link);
                                        System.out.println("---------------------------------");
                                    }
                                }
                            }


                        }

                    }

                    break;
                case "3":
                    Data newData = new Data();


                    System.out.println("Введите имя ссылки:");
                    newData.name_link = in.nextLine();
                    System.out.println("Введите описание ссылки:");
                    newData.discription_link = in.nextLine();
                    System.out.println("Введите ссылку:");
                    newData.link = in.nextLine();

                    wrf.WriterWRT(mapData.AddItemData(newData));

                    break;
                case "4":
                    check = false;
                    break;
                default:
                    System.out.println("Нет такого пункта, попробуйте снова.");

            }


        }
    }
}