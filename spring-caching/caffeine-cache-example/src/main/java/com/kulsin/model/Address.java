package com.kulsin.model;

public class Address {

    private final String street;
    private final String city;
    private final String country;
    private final int pin;

    public Address(String street, String city, String country, int pin) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.pin = pin;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public int getPin() {
        return pin;
    }
}
