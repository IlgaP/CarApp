package com.example.carapp.service;

import com.example.carapp.classes.Car;
import com.example.carapp.repository.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;



@ExtendWith({MockitoExtension.class})
public class CarServiceTest {

    @Mock
    CarRepository carRepository;

    @InjectMocks
    CarService carService;

    Car car = new Car(
            "audi",
            "a4",
            2020,
            "165U8764F88",
            "AA1234");

    @Test
    @DisplayName("Should be able to add a car")
    public void testAddNewCar() {

        Mockito.doAnswer(invocation -> {
            Car addCar = invocation.getArgument(0);
            Assertions.assertEquals(car, addCar);
            return new Car(
                    1,
                    car.getMake(),
                    car.getModel(),
                    car.getYear(),
                    car.getVINNumber(),
                    car.getRegistrationNumber()
            );
        }).when(carRepository).save(car);

        given(carRepository.save(car)).willReturn(car);
        Car savedCar = carService.addCar(car);
        assertThat(savedCar).isNotNull();
        verify(carRepository).save(ArgumentMatchers.any(Car.class));
    }

    @Test
    @DisplayName("Should not add same car twice")
    public void shouldNotAddSameCarTwice() {

        carService.addCar(car);

        Car car2 = new Car(
                "audi",
                "a4",
                2020,
                "165U8764F88",
                "AA1234"
        );

        given(carRepository.findByMakeAndModelAndYearAndVINNumberAndRegistrationNumber(
                car2.getMake(),
                car2.getModel(),
                car2.getYear(),
                car2.getVINNumber(),
                car2.getRegistrationNumber()
        )).willReturn(Optional.of(car2));

        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class,
                () -> carService.addCar(car2));

        Assertions.assertEquals(409, exception.getRawStatusCode());
    }

    @Test
    @DisplayName("Should return list of all cars")
    public void shouldReturnAllCars() {
        List<Car> cars = new ArrayList<>();
        cars.add(car);
        cars.add(new Car("bmw", "aa", 2021, "876GG44321", "TT4545"));

        given(carRepository.findAll()).willReturn(cars);

        List<Car> expectedCars = carService.getAllCars();

        Assertions.assertEquals(expectedCars, cars);
    }

    @Test
    @DisplayName("Should add list of new cars, erase existing cars")
    public void addNewCarsEraseExisting() {

        given(carRepository.save(car)).willReturn(car);
        carService.addCar(car);

        List<Car> newCars = new ArrayList<>();
        newCars.add(new Car("toyota", "xx", 2020, "956SR456H55", "OI8899"));
        newCars.add(new Car("audi", "y6", 2021, "123SRYY6H00", "PP8329"));

        given(carRepository.saveAll(newCars)).willReturn(newCars);

        List<Car> expectedCars = carService.addNewCars(newCars);

        Assertions.assertEquals(expectedCars, newCars);

    }
}
