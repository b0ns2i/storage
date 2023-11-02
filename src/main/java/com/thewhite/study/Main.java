package com.thewhite.study;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;


public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Рабос с файлом: " + args[0]);
        WriterReaderFile wrf = new WriterReaderFile(args[0]);
        DataRepository dataRepository = new DataRepository(wrf);

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
                    runTheFirstMenuItem(dataRepository, in);
                    break;
                case "2":
                    runTheSecondMenuItem(dataRepository, in);
                    break;
                case "3":
                    runTheThirdMenuItem(dataRepository, in, wrf);
                    break;
                case "4":
                    check = false;
                    break;
                default:
                    System.out.println("Нет такого пункта, попробуйте снова.");

            }


        }
    }

    public static void runTheFirstMenuItem(DataRepository dataRepository, Scanner in) {
        System.out.println("Введите ID записи. Нажмите \":q\" для выхода в главное меню.");
        boolean checkGTIID = true;
        while (checkGTIID) {
            String uuidStr = in.nextLine();
            if (uuidStr.equals(":q")) {
                checkGTIID = false;
            } else {

                Data data = dataRepository.getDataItemID(uuidStr);

                if (data == null) {
                    System.out.println("Запись не найдена!");
                } else {
                    System.out.printf(" Название записис: %s;\n Описание записи: %s;\n Ссылка: %s;\n",
                            data.name,
                            data.discription,
                            data.link);
                    System.out.println("---------------------------------");
                    System.out.println("Введите ID или \":q\" для выхода");

                }
            }
        }
    }

    public static void runTheSecondMenuItem(DataRepository dataRepository, Scanner in){
        boolean checkSSD = true;
        while (checkSSD) {
            System.out.println("Введите имя/часть имени записи. Нажмите \":q\" для выхода в главное меню.");
            String subname = in.nextLine();
            if (subname.equals(":q")) {
                checkSSD = false;
            } else {
                if (subname.equals("")) {
                    System.out.println("Некорректная подстрока");
                } else {
                    ArrayList<Data> listData = dataRepository.searchSubnameData(subname.toLowerCase());
                    if (listData.size() == 0) {
                        System.out.println("Не удалось найти запись");
                    } else {
                        for (Data data : listData) {
                            System.out.printf(" Название записис: %s;\n Описание записи: %s;\n Ссылка: %s;\n",
                                    data.name,
                                    data.discription,
                                    data.link);
                            System.out.println("---------------------------------");
                        }
                    }
                }


            }

        }
    }

    public static void runTheThirdMenuItem(DataRepository dataRepository, Scanner in, WriterReaderFile wrf){
        System.out.println("Введите имя ссылки:");
        String name = in.nextLine();
        System.out.println("Введите описание ссылки:");
        String discription = in.nextLine();
        System.out.println("Введите ссылку:");
        String link = in.nextLine();
        Data newData = new Data(UUID.randomUUID(), name, discription, link);

        wrf.write(dataRepository.addItemData(newData));
    }
}
