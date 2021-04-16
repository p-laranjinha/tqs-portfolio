package com.example.p2car;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Car {

    @Id
    @GeneratedValue
    private Long carId;
    private String maker;
    private String model;

    public Car() {

    }

    public Car(String maker, String model) {
        this.maker = maker;
        this.model = model;
    }
}
