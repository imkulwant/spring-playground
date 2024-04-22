package com.kulsin.model;

public class DataObject {

    private final String data;

    private static int objectCounter = 0;

    public DataObject(String data) {
        this.data = data;
    }

    public static DataObject get(String data) {
        objectCounter++;
        return new DataObject(data);
    }

    public String getData() {
        return data;
    }

    public static int getObjectCounter() {
        return objectCounter;
    }
}
