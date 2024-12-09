package org.eduardomaravill.sdd_catalogo.services.cars_services.implementation;

import jakarta.transaction.Transactional;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.ClassesDto;
import org.eduardomaravill.sdd_catalogo.exceptions.ResourceDuplicateException;
import org.eduardomaravill.sdd_catalogo.exceptions.ResourceNotFoundException;
import org.eduardomaravill.sdd_catalogo.models.cars_models.Classes;
import org.eduardomaravill.sdd_catalogo.repositories.cars_repositories.IClassesRepository;
import org.eduardomaravill.sdd_catalogo.services.MapperService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.IClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassesServiceImpl implements IClassesService {

    private static final String CLASS_MESSAGE = "Class";

    private final IClassesRepository classesRepository;

    private final MapperService mapperService;

    @Autowired
    public ClassesServiceImpl(IClassesRepository classesRepository, MapperService mapperService) {
        this.classesRepository = classesRepository;
        this.mapperService = mapperService;
    }

    @Override
    @Cacheable(value = "classes", key = "#id")
    public ClassesDto getClasses(Long id) {
        Classes classes = classesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(CLASS_MESSAGE,id));
        return mapperService.convertToDto(classes);
    }

    @Override
    @Transactional
    @CacheEvict(value = "allClasses", allEntries = true)
    public void createClass(ClassesDto classes) {
        if(classesRepository.existsByName(classes.getName())){
            throw new ResourceDuplicateException(CLASS_MESSAGE, classes.getName());
        }
        Classes classesEntity = mapperService.convertToEntity(classes);
        classesRepository.save(classesEntity);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"allClasses","classes"}, allEntries = true)
    public void updateClass(Long id, ClassesDto classes) {
        verifyExists(id);
        classes.setId(id);
        Classes classesEntity = mapperService.convertToEntity(classes);
        classesRepository.save(classesEntity);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"allClasses","classes"}, allEntries = true)
    public void deleteClass(Long id) {
        verifyExists(id);
        classesRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "allClasses")
    public List<ClassesDto> getAllClasses() {
        return classesRepository.findAll().stream().map(mapperService::convertToDto).toList();
    }

    private void verifyExists(Long id){
        if (!classesRepository.existsById(id)){
            throw new ResourceNotFoundException(CLASS_MESSAGE, id);
        }
    }
}
