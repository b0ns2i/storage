package com.thewhite.study;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args).close();
    }


    @Bean
    public CommandLineRunner run() {
        return args -> {
            System.out.println("Это моя перва программа на спринг в консоле!!!");
            boolean check = true;

            Scanner in = new Scanner(System.in);
            System.out.println("Рабос с файлом: " + "C:\\TheWhite\\BackEnd\\ДЗ2\\storage\\src\\DB\\DB_links.json");
            WriterReaderFile wrf = new WriterReaderFile("C:\\TheWhite\\BackEnd\\ДЗ2\\storage\\src\\DB\\DB_links.json");
            DataRepository dataRepository = new DataRepository(wrf);


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

        };
    }


    public static void runTheFirstMenuItem(DataRepository dataRepository, Scanner in) {
        System.out.println("Введите ID записи. Нажмите \":q\" для выхода в главное меню.");
        boolean checkGTIID = true;
        while (checkGTIID) {
            String uuidStr = in.nextLine();
            if (uuidStr.equals(":q")) {
                checkGTIID = false;
            } else {
                Data data = new Data();
                try {
                    data = dataRepository.getDataItemID(uuidStr);
                }
                catch (Exception ex){

                }

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

    public static void runTheSecondMenuItem(DataRepository dataRepository, Scanner in) {
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

    public static void runTheThirdMenuItem(DataRepository dataRepository, Scanner in, WriterReaderFile wrf) {
        System.out.println("Введите имя ссылки:");
        String name = in.nextLine();
        System.out.println("Введите описание ссылки:");
        String discription = in.nextLine();
        System.out.println("Введите ссылку:");
        String link = in.nextLine();

        try {

            wrf.write(dataRepository.addItemData(Data.builder()
                    .id(UUID.randomUUID())
                    .name(name)
                    .discription(discription)
                    .link(link).build()));
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
