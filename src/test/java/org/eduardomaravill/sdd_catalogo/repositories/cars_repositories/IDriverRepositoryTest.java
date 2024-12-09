package org.eduardomaravill.sdd_catalogo.repositories.cars_repositories;

import org.eduardomaravill.sdd_catalogo.models.cars_models.Driver;
import org.eduardomaravill.sdd_catalogo.models.cars_models.InitSkid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {"spring.profiles.active=test"})
class IDriverRepositoryTest {

    @Autowired
    private IDriverRepository driverRepository;

    @Autowired
    private IInitSkidRepository initSkidRepository;

    private Driver driver;
    private InitSkid initSkid;

    @BeforeEach
    void setUp() {
        driver = new Driver();
        driver.setDrive(50);
        driver.setDirection(5);
        driver.setDownForce(3);
        driver.setControlTraction(true);
        initSkid = new InitSkid();
        initSkid.setSkidType("POR DEFECTO");
    }


    @Test
    @DisplayName("Test save Driver")
    void testSaveDriver() {
        initSkidRepository.save(initSkid);
        driver.setInitSkid(initSkid);
        driverRepository.save(driver);
        Optional<Driver> savedDriver = driverRepository.findById(driver.getId());
        assertTrue(savedDriver.isPresent());
        assertEquals(50,savedDriver.get().getDrive());
    }

    @Test
    @DisplayName("Test update Driver")
    void testUpdateDriver() {
        initSkidRepository.save(initSkid);
        driver.setInitSkid(initSkid);
        driverRepository.save(driver);
        driver.setDrive(100);
        driverRepository.save(driver);
        Optional<Driver> savedDriver = driverRepository.findById(driver.getId());
        assertTrue(savedDriver.isPresent());
        assertEquals(100, savedDriver.get().getDrive());
    }

    @Test
    @DisplayName("Test delete Driver")
    void testDeleteDriver() {
        initSkidRepository.save(initSkid);
        driver.setInitSkid(initSkid);
        driverRepository.save(driver);
        driverRepository.deleteById(driver.getId());
        Optional<Driver> deletedDriver = driverRepository.findById(driver.getId());
        assertFalse(deletedDriver.isPresent());
    }
}