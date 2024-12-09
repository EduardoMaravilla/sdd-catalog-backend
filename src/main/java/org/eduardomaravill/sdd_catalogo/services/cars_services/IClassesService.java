package org.eduardomaravill.sdd_catalogo.services.cars_services;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.ClassesDto;

import java.util.List;

public interface IClassesService {
    ClassesDto getClasses(Long id);

    void createClass(ClassesDto classes);

    void updateClass(Long id, ClassesDto classes);

    void deleteClass(Long id);

    List<ClassesDto> getAllClasses();
}
