package com.example.chatapplication;

public class ContactModel {
    private String countryCode;
    private String name;
    private String phoneNumber;

    public ContactModel(String countryCode, String name, String phoneNumber) {  // ✅ Fix: Accept 3 parameters
        this.countryCode = countryCode;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getCountryCode() {  // ✅ Getter for country code
        return countryCode;
    }

    public String getName() {  // ✅ Getter for name
        return name;
    }

    public String getPhoneNumber() {  // ✅ Getter for phone number
        return phoneNumber;
    }
}
