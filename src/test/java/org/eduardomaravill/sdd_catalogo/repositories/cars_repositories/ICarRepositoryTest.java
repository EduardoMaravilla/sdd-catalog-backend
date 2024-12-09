package org.eduardomaravill.sdd_catalogo.repositories.cars_repositories;

import org.eduardomaravill.sdd_catalogo.models.cars_models.Car;
import org.eduardomaravill.sdd_catalogo.models.cars_models.Maker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {"spring.profiles.active=test"})
class ICarRepositoryTest {

    @Autowired
    private ICarRepository carRepository;

    @Autowired
    private IMakersRepository makerRepository;

    @Test
    @DisplayName("Test save Car")
    void testSaveCar() {
        Car car = new Car();
        car.setModel("Mustang");
        car.setCarYear("1964");
        Maker maker = new Maker();
        maker.setName("Ford");
        makerRepository.save(maker);
        car.setMaker(maker);
        carRepository.save(car);
        Optional<Car> savedCar = carRepository.findById(car.getId());
        assertNotNull(savedCar);
        assertTrue(savedCar.isPresent());
        assertEquals("Mustang", savedCar.get().getModel());
    }

    @Test
    @DisplayName("Test update Car")
    void testUpdateCar() {
        Car car = new Car();
        car.setModel("Mustang");
        car.setCarYear("1964");
        Maker maker = new Maker();
        maker.setName("Ford");
        makerRepository.save(maker);
        car.setMaker(maker);
        carRepository.save(car);
        Optional<Car> savedCar = carRepository.findById(car.getId());
        assertNotNull(savedCar);
        assertTrue(savedCar.isPresent());
        assertEquals("Mustang", savedCar.get().getModel());
        car.setModel("Ferrari");
        carRepository.save(car);
        Optional<Car> updatedCar = carRepository.findById(car.getId());
        assertNotNull(updatedCar);
        assertTrue(updatedCar.isPresent());
        assertEquals("Ferrari", updatedCar.get().getModel());
    }

    @Test
    @DisplayName("Test delete Car")
    void testDeleteCar() {
        Car car = new Car();
        car.setModel("Mustang");
        car.setCarYear("1964");
        Maker maker = new Maker();
        maker.setName("Ford");
        makerRepository.save(maker);
        car.setMaker(maker);
        carRepository.save(car);
        Optional<Car> savedCar = carRepository.findById(car.getId());
        assertNotNull(savedCar);
        assertTrue(savedCar.isPresent());
        carRepository.deleteById(car.getId());
        Optional<Car> deletedCar = carRepository.findById(car.getId());
        assertFalse(deletedCar.isPresent());
    }

}