package org.eduardomaravill.sdd_catalogo.services.cars_services.implementation;

import jakarta.transaction.Transactional;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.LevelDto;
import org.eduardomaravill.sdd_catalogo.exceptions.ResourceDuplicateException;
import org.eduardomaravill.sdd_catalogo.exceptions.ResourceNotFoundException;
import org.eduardomaravill.sdd_catalogo.models.cars_models.LevelEntity;
import org.eduardomaravill.sdd_catalogo.repositories.cars_repositories.ILevelRepository;
import org.eduardomaravill.sdd_catalogo.services.MapperService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.ILevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelServiceImpl implements ILevelService {

    private static final String LEVEL_MESSAGE = "Level";

    private final ILevelRepository levelRepository;

    private final MapperService mapperService;

    @Autowired
    public LevelServiceImpl(ILevelRepository levelRepository, MapperService mapperService) {
        this.levelRepository = levelRepository;
        this.mapperService = mapperService;
    }

    @Override
    @Cacheable(value = "levels", key = "#id")
    public LevelDto getLevel(Long id) {
        LevelEntity levelEntity = levelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(LEVEL_MESSAGE, id));
        return mapperService.convertToDto(levelEntity);
    }

    @Override
    @Transactional
    @CacheEvict(value = "allLevels", allEntries = true)
    public void createLevel(LevelDto levelDto) {
        if (levelRepository.existsByName(levelDto.getLevel())) {
            throw new ResourceDuplicateException(LEVEL_MESSAGE, levelDto.getLevel());
        }
        LevelEntity levelEntity = mapperService.convertToEntity(levelDto);
        levelRepository.save(levelEntity);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"allLevels", "levels"}, allEntries = true)
    public void updateLevel(Long id, LevelDto levelDto) {
        verifyExists(id);
        levelDto.setId(id);
        LevelEntity levelEntity = mapperService.convertToEntity(levelDto);
        levelRepository.save(levelEntity);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"allLevels", "levels"}, allEntries = true)
    public void deleteLevel(Long id) {
        verifyExists(id);
        levelRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "allLevels")
    public List<LevelDto> getAllLevels() {
        return levelRepository.findAll().stream().map(mapperService::convertToDto).toList();
    }

    private void verifyExists(Long id) {
        if (!levelRepository.existsById(id)) {
            throw new ResourceNotFoundException(LEVEL_MESSAGE, id);
        }
    }
}