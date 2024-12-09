package org.eduardomaravill.sdd_catalogo.repositories.cars_repositories;

import org.eduardomaravill.sdd_catalogo.models.cars_models.Classes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {"spring.profiles.active=test"})
class IClassesRepositoryTest {

    @Autowired
    private IClassesRepository classesRepository;

    @Test
    @DisplayName("Test save Classes")
    void testSaveClasses(){
        Classes clazz = new Classes();
        clazz.setName("A+");
        classesRepository.save(clazz);
        Optional<Classes> savedClasses = classesRepository.findById(clazz.getId());
        assertTrue(savedClasses.isPresent());
        assertEquals(clazz.getName(), savedClasses.get().getName());
    }

    @Test
    @DisplayName("Test update Classes")
    void testUpdateClasses(){
        Classes clazz = new Classes();
        clazz.setName("A");
        classesRepository.save(clazz);
        clazz.setName("A+");
        classesRepository.save(clazz);
        Optional<Classes> savedClasses = classesRepository.findById(clazz.getId());
        assertTrue(savedClasses.isPresent());
        assertEquals(clazz.getName(), savedClasses.get().getName());
    }

    @Test
    @DisplayName("Test delete Classes")
    void testDeleteClasses(){
        Classes clazz = new Classes();
        clazz.setName("A");
        classesRepository.save(clazz);
        classesRepository.deleteById(clazz.getId());
        Optional<Classes> deletedClasses = classesRepository.findById(clazz.getId());
        assertFalse(deletedClasses.isPresent());
    }
}