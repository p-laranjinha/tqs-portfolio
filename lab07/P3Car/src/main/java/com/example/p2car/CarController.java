package com.example.p2car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CarController {

    @Autowired
    private CarManagerService carManagerService;

    @PostMapping("/cars")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        Car saved = carManagerService.save(car);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carManagerService.getAllCars();
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable(value = "id") Long id) throws Exception {
        Car car = carManagerService.getCarDetails(id)
                .orElseThrow(() -> new Exception());
        return ResponseEntity.ok().body(car);
    }

}
