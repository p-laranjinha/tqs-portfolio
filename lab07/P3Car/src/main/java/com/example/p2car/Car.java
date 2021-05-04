package com.example.p2car;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long carId;
    @Column(name = "maker")
    private String maker;
    @Column(name = "model")
    private String model;

    public Car() {

    }

    public Car(String maker, String model) {
        this.maker = maker;
        this.model = model;
    }
}
