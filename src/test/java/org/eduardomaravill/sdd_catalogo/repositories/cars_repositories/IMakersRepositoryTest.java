package org.eduardomaravill.sdd_catalogo.repositories.cars_repositories;

import org.eduardomaravill.sdd_catalogo.models.cars_models.Maker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {"spring.profiles.active=test"})
class IMakersRepositoryTest {
    @Autowired
    private IMakersRepository makersRepository;

    @Test
    @DisplayName("Test save Maker")
    void testSaveMaker(){
        Maker maker =  new Maker();
        maker.setName("Ford");
        makersRepository.save(maker);
        Optional<Maker> savedMaker = makersRepository.findById(maker.getId());
        assertTrue(savedMaker.isPresent());
        assertEquals("Ford", savedMaker.get().getName());
    }

    @Test
    @DisplayName("Test update Maker")
    void testGetMaker(){
        Maker maker = new Maker();
        maker.setName("Ford");

        makersRepository.save(maker);
        Optional<Maker> savedMaker = makersRepository.findById(maker.getId());

        assertTrue(savedMaker.isPresent());
        assertEquals("Ford", savedMaker.get().getName());

        maker.setName("Chevrolet");

        makersRepository.save(maker);

        savedMaker = makersRepository.findById(maker.getId());

        assertTrue(savedMaker.isPresent());
        assertEquals("Chevrolet", savedMaker.get().getName());
    }

    @Test
    @DisplayName("Test delete Maker")
    void testDeleteMaker(){
        Maker maker = new Maker();
        maker.setName("Ford");
        makersRepository.save(maker);

        makersRepository.deleteById(maker.getId());

        Optional<Maker> deletedMaker = makersRepository.findById(maker.getId());

        assertFalse(deletedMaker.isPresent());
    }
}