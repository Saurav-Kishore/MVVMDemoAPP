package com.example.mvvmdemoapp.model;

public class ContactData {
    private String mName;
    private String mNumber;

    public ContactData(String name, String number) {
        this.mName = name;
        this.mNumber = number;
    }

    public String getName() {
        return mName;
    }

    public String getNumber() {
        return mNumber;
    }
}
