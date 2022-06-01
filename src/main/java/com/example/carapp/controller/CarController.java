package com.example.carapp.controller;

import com.example.carapp.model.Car;
import com.example.carapp.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @Operation(summary = "Add a new car")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Car added successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Car.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Car already exists in the database",
                    content = @Content)}
    )
    @PutMapping("/addCar")
    @ResponseStatus(HttpStatus.CREATED)
    public Car addCar(@Valid @RequestBody Car car) {
        return carService.addCar(car);
    }

    @Operation(summary = "Get all cars for the given make")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found cars with the given make",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Car.class))}),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
    @GetMapping("/getCars")
    public List<Car> getCars(@RequestParam String make) {
        return carService.getCars(make);
    }


    @Operation(summary = "Get all cars")
    @GetMapping("/getAllCars")
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @Operation(summary = "Add new car list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added new cars to the database, existing data erased",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Car.class))}),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
    @PutMapping("/newCars")
    @ResponseStatus(HttpStatus.OK)
    public List<Car> addNewCars(@RequestBody List<Car> carList) {
        return carService.addNewCars(carList);
    }
}
