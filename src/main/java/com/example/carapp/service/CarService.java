package com.example.carapp.service;

import com.example.carapp.classes.AddCarRequest;
import com.example.carapp.classes.Car;
import com.example.carapp.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getCars(String make) {
        return carRepository.carListByMake(make.toLowerCase());
    }

    public Car addCar(Car car) {
        Optional<Car> maybeCar = carRepository.findByMakeAndModelAndYearAndVINNumberAndRegistrationNumber(
                car.getMake(),
                car.getModel(),
                car.getYear(),
                car.getVINNumber(),
                car.getRegistrationNumber()
        );
        if (maybeCar.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        return carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public List<Car> addNewCars(List<Car> carList) {
        carRepository.deleteAll();
        return carRepository.saveAll(carList);
    }
}
