package com.example.carapp.classes;

import javax.validation.constraints.NotNull;

public class AddCarRequest {
    @NotNull
    private String make;
    @NotNull
    private String model;
    @NotNull
    private int year;
    @NotNull
    private String VINNumber;
    @NotNull
    private String registrationNumber;

    public AddCarRequest() {
    }

    public AddCarRequest(String make, String model, int year, String VINNumber, String registrationNumber) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.VINNumber = VINNumber;
        this.registrationNumber = registrationNumber;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getVINNumber() {
        return VINNumber;
    }

    public void setVINNumber(String VINNumber) {
        this.VINNumber = VINNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
}
