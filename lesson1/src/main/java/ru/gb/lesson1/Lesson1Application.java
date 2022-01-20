package ru.gb.lesson1;

import ru.gb.lesson1.first.Person;

public class Lesson1Application {
    public static void main(String[] args) {
        Person myPerson = new Person.Builder()
                .addLastName("Jane")
                .addFirstName("Doe")
                .addAge(32)
                .addCountry("Russia")
                .addGender("male")
                .addPhone("+71234567890")
                .addMiddleName("Ivanovich")
                .build();
    }
}
