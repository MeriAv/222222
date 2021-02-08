package ru.netology;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Generator {


       private Faker faker;
        private LocalDate today = LocalDate.now();
        private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");


        public String formateDate (int plusDays) {
            LocalDate newDate = today.plusDays(plusDays);
            return formatter.format(newDate);
        }

        public String getCity() {
            String[] myCityList = new String[]{"Абакан", "Анадырь", "Архангельск", "Астрахань", "Барнаул", "Владикавказ", "Горно-Алтайск", "Йошкар-Ола", "Казань", "Калининград", "Калуга", "Краснодар", "Магас", "Махачкала", "Нарьян-Мар", "Салехард", "Самара", "Саранск", "Саратов", "Хабаровск", "Ханты-Мансийск", "Южно-Сахалинск", "Великий Новгород", "Владивосток", "Владимир", "Вологда", "Рязань", "Биробиджан", "Чебоксары", "Москва", "Санкт-Петербург", "Ульяновск", "Симферополь", "Ростов-на-Дону"};
            int city = (int) Math.floor(Math.random() * myCityList.length);
            return myCityList[city];
        }

        public String getName() {
            faker = new Faker(new Locale("ru"));
            return (faker.name().lastName()+(" ")+faker.name().firstName());
        }

        public String getPhone() {
            faker = new Faker(new Locale("ru"));
            return faker.phoneNumber().phoneNumber();
        }


}
