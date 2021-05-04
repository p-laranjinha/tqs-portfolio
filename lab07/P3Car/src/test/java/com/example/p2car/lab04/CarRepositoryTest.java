package com.example.p2car.lab04;

import com.example.p2car.Car;
import com.example.p2car.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private TestEntityManager manager;

    @Autowired
    private CarRepository repository;

    @Test
    void whenValidID_thenReturnCar() {
        Car c1 = new Car("maker", "model");
        manager.persistAndFlush(c1);

        Car found = repository.findByCarId(c1.getCarId());
        assertThat(found).isEqualTo(c1);
    }

    @Test
    void whenInvalidID_thenReturnNothing() {
        Car found = repository.findByCarId(1L);
        assertThat(found).isNull();
    }

    @Test
    void whenGetAll_thenReturnList() {
        Car c1 = new Car("maker1", "model1");
        Car c2 = new Car("maker2", "model2");
        Car c3 = new Car("maker3", "model3");
        manager.persist(c1);
        manager.persist(c2);
        manager.persist(c3);
        manager.flush();

        List<Car> cars = repository.findAll();

        assertThat(cars).hasSize(3).extracting(Car::getMaker).contains("maker1", "maker2", "maker3");
    }

}
