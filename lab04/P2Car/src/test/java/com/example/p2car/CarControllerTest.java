package com.example.p2car;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

@WebMvcTest
public class CarControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    CarManagerService service;

    @Test
    void whenPostCar_thenReturnCar() throws Exception {
        Car c1 = new Car("maker", "model"); c1.setCarId(2L);

        when(service.save(Mockito.any())).thenReturn(c1);

        mvc.perform(post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(c1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.maker", is("maker")));

        verify(service, times(1)).save(c1);
    }

    @Test
    void whenGetCarByValidId_thenReturnCar() throws Exception {
        Car c1 = new Car("maker", "model"); c1.setCarId(2L);

        when(service.getCarDetails(c1.getCarId())).thenReturn(Optional.of(c1));

        mvc.perform(get("/api/cars/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("maker").value("maker"))
                .andExpect(jsonPath("model").value("model"));

        verify(service, times(1)).getCarDetails(c1.getCarId());
    }

    @Test
    void whenGetAllCars_thenReturnCarList() throws Exception {
        Car c1 = new Car("maker", "model"); c1.setCarId(2L);
        Car c2 = new Car("maker2", "model2"); c1.setCarId(3L);
        List<Car> allCars = Arrays.asList(c1, c2);

        when(service.getAllCars()).thenReturn(allCars);

        mvc.perform(get("/api/cars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].maker", is(c1.getMaker())))
                .andExpect(jsonPath("$[1].maker", is(c2.getMaker())));

        verify(service, times(1)).getAllCars();
    }

    @Test
    void whenGetCarByInvalidId_thenReturnNothing() {
        Car c1 = new Car("maker", "model"); c1.setCarId(2L);

        when(service.getCarDetails(c1.getCarId())).thenReturn(Optional.of(c1));
        try {
            mvc.perform(get("/api/cars/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("maker").value("maker"))
                    .andExpect(jsonPath("model").value("model"));
        } catch (Exception e) {
            return;
        }
        fail();
    }
}
