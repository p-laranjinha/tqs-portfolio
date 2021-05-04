package com.example.p2car.lab04;

import com.example.p2car.Car;
import com.example.p2car.CarManagerService;
import com.example.p2car.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock(lenient = true)
    private CarRepository repository;

    @InjectMocks
    private CarManagerService service;

    @BeforeEach
    public void setUp() {
        Car c1 = new Car("maker1", "model1"); c1.setCarId(0L);
        Car c2 = new Car("maker2", "model2"); c2.setCarId(1L);
        Car c3 = new Car("maker3", "model3"); c3.setCarId(2L);
        List<Car> allCars = Arrays.asList(c1, c2, c3);

        when(repository.findByCarId(c1.getCarId())).thenReturn(c1);
        when(repository.findByCarId(c2.getCarId())).thenReturn(c2);
        when(repository.findByCarId(-1L)).thenReturn(null);
        when(repository.findAll()).thenReturn(allCars);
    }

    @Test
    void whenValidId_thenReturnCar() {
        Car c1 = service.getCarDetails(0L).orElse(null);
        Car c2 = service.getCarDetails(1L).orElse(null);

        assertThat(c1.getMaker()).isEqualTo("maker1");
        assertThat(c2.getMaker()).isEqualTo("maker2");

        verify(repository, times(2)).findByCarId(Mockito.any());
    }

    @Test
    void whenInvalidId_thenReturnNull() {
        Car c = service.getCarDetails(-1L).orElse(null);

        assertThat(c).isNull();

        verify(repository, times(1)).findByCarId(Mockito.any());
    }

    @Test
    void whenGetAllCars_thenReturnCarList() {
        List<Car> cars = service.getAllCars();

        assertThat(cars).hasSize(3).extracting(Car::getMaker).contains("maker1", "maker2", "maker3");

        verify(repository, times(1)).findAll();
    }
}
