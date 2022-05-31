package com.example.carapp.classes;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @SequenceGenerator(
            name = "car_sequence",
            sequenceName = "car_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "car_sequence")
    @Column(name = "car_id")
    private int carID;
    @NotNull
    private String make;
    @NotNull
    private String model;
    @NotNull
    @Column(name = "year_of_manufacture")
    private int year;
    @NotNull
    @Column(name = "vin_number")
    private String VINNumber;
    @NotNull
    @Column(name = "registration_number")
    private String registrationNumber;


    public Car(String make, String model, int year, String VINNumber, String registrationNumber) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.VINNumber = VINNumber;
        this.registrationNumber = registrationNumber;
    }

    public Car(int carID, String make, String model, int year, String VINNumber, String registrationNumber) {
        this.carID = carID;
        this.make = make;
        this.model = model;
        this.year = year;
        this.VINNumber = VINNumber;
        this.registrationNumber = registrationNumber;
    }

    public Car() {
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
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
