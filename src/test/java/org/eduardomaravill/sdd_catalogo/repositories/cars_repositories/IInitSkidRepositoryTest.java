package org.eduardomaravill.sdd_catalogo.repositories.cars_repositories;

import org.eduardomaravill.sdd_catalogo.models.cars_models.InitSkid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {"spring.profiles.active=test"})
class IInitSkidRepositoryTest {

    @Autowired
    private IInitSkidRepository initSkidRepository;

    private InitSkid initSkid;

    @BeforeEach
    void setUp() {
        initSkid = new InitSkid();
        initSkid.setSkidType("NO");
    }

    @Test
    @DisplayName("Test save InitSkid")
    void testSaveInitSkid(){
        initSkidRepository.save(initSkid);
        assertNotNull(initSkid.getId());
        Optional<InitSkid> initSkidSave = initSkidRepository.findById(initSkid.getId());
        assertTrue(initSkidSave.isPresent());
        assertEquals(initSkid.getSkidType(), initSkidSave.get().getSkidType());
    }

    @Test
    @DisplayName("Test update InitSkid")
    void testUpdateInitSkid(){
        initSkid.setSkidType("POR DEFECTO");
        initSkidRepository.save(initSkid);
        Optional<InitSkid> initSkidSave = initSkidRepository.findById(initSkid.getId());
        assertTrue(initSkidSave.isPresent());
        assertEquals(initSkid.getSkidType(), initSkidSave.get().getSkidType());
    }

    @Test
    @DisplayName("Test delete InitSkid")
    void testDeleteInitSkid(){
        initSkidRepository.save(initSkid);
        initSkidRepository.deleteById(initSkid.getId());
        Optional<InitSkid> initSkidSave = initSkidRepository.findById(initSkid.getId());
        assertFalse(initSkidSave.isPresent());
    }
}