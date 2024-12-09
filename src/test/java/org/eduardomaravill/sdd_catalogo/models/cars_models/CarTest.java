package org.eduardomaravill.sdd_catalogo.models.cars_models;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class CarTest {

    private Car car;
    private Validator validator;

    @BeforeEach
    void setUp() {
        Maker maker = new Maker(25L, "Ford");
        car = new Car(10L, maker, "Mustang", "1964");
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    @DisplayName("Test get ID")
    void getId() {
        assertEquals(10L, car.getId());
    }

    @Test
    @DisplayName("Test get Maker")
    void getMaker() {
        assertEquals("Ford", car.getMaker().getName());
    }

    @Test
    @DisplayName("Test get Model")
    void getModel() {
        assertEquals("Mustang", car.getModel());
    }

    @Test
    @DisplayName("Test get Year")
    void getYear() {
        assertEquals("1964", car.getCarYear());
    }

    @Test
    @DisplayName("Test change of ID")
    void setId() {
        car.setId(20L);
        assertEquals(20L, car.getId());
    }

    @Test
    @DisplayName("Test change of maker")
    void setMaker() {
        Maker maker = new Maker(30L, "SRT");
        car.setMaker(maker);
        assertEquals("SRT", car.getMaker().getName());
    }

    @Test
    @DisplayName("Test not null of Maker")
    void testValidMakerInCar() {
        car.setMaker(null);
        assertThrows(ConstraintViolationException.class, () -> validatedCar(car));
    }

    @Test
    @DisplayName("Test change of model")
    void setModel() {
        car.setModel("Focus");
        assertEquals("Focus", car.getModel());
    }

    @Test
    @DisplayName("Test not null, not empty and size 50 of model")
    void testValidModelInCar() {
        car.setModel(null);
        assertThrows(ConstraintViolationException.class, () -> validatedCar(car));
        car.setModel("");
        assertThrows(ConstraintViolationException.class, () -> validatedCar(car));
        car.setModel("a".repeat(51));
        assertThrows(ConstraintViolationException.class, () -> validatedCar(car));
    }

    @Test
    @DisplayName("Test change of year")
    void setYear() {
        car.setCarYear("1965");
        assertEquals("1965", car.getCarYear());
    }

    @Test
    @DisplayName("Test not null, not empty and size 4 of year")
    void testValidYearInCar() {
        car.setCarYear(null);
        assertThrows(ConstraintViolationException.class, () -> validatedCar(car));
        car.setCarYear("");
        assertThrows(ConstraintViolationException.class, () -> validatedCar(car));
        car.setCarYear("a".repeat(5));
        assertThrows(ConstraintViolationException.class, () -> validatedCar(car));
    }

    @Test
    @DisplayName("Test equals")
    void testEquals() {
        Car car2 = new Car(10L, new Maker(25L, "Ford"), "Mustang", "1964");
        assertEquals(car, car2);
    }

    @Test
    @DisplayName("Test count fields and methods")
    void testNumberOfMethodsAndFields() {
        assertEquals(4, Car.class.getDeclaredFields().length);
        assertEquals(12, Car.class.getDeclaredMethods().length);
    }

    private void validatedCar(Car car) {
        Set<ConstraintViolation<Car>> violations = validator.validate(car);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}