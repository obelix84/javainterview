package ru.gb.lesson1.first;

public class Person {
    private String firstName;
    private String lastName;
    private String middleName;
    private String country;
    private String address;
    private String phone;
    private int age;
    private String gender;

    public static class Builder {
        Person person;

        public Builder() {
            person = new Person();
        }

        public Builder addFirstName(String firstName) {
            person.firstName = firstName;
            return this;
        }

        public Builder addLastName(String lastName) {
            person.lastName = lastName;
            return this;
        }

        public Builder addMiddleName(String middleName) {
            person.middleName = middleName;
            return this;
        }

        public Builder addCountry(String country) {
            person.country = country;
            return this;
        }

        public Builder addAddress(String address) {
            person.address = address;
            return this;
        }

        public Builder addPhone(String phone) {
            person.phone = phone;
            return this;
        }

        public Builder addAge(int age) {
            person.age = age;
            return this;
        }

        public Builder addGender(String gender) {
            person.gender = gender;
            return this;
        }

        public Person build(){
            return person;
        }
    }

}
