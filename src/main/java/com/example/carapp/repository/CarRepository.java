package com.example.carapp.repository;

import com.example.carapp.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer> {

    boolean existsByMakeAndModelAndYearAndVINNumberAndRegistrationNumber (
            String make,
            String model,
            int year,
            String VINNumber,
            String registrationNumber
    );

    @Query("select c from Car c where lower(c.make) = :make")
    List<Car> carListByMake (@Param("make") String make);
}
