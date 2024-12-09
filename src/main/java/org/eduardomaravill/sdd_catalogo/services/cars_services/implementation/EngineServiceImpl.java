package org.eduardomaravill.sdd_catalogo.services.cars_services.implementation;

import jakarta.transaction.Transactional;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.EngineDto;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.LevelDto;
import org.eduardomaravill.sdd_catalogo.exceptions.DataNotFoundException;
import org.eduardomaravill.sdd_catalogo.exceptions.ResourceDuplicateException;
import org.eduardomaravill.sdd_catalogo.exceptions.ResourceNotFoundException;
import org.eduardomaravill.sdd_catalogo.models.cars_models.Engine;
import org.eduardomaravill.sdd_catalogo.repositories.cars_repositories.IEngineRepository;
import org.eduardomaravill.sdd_catalogo.services.MapperService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.IEngineService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.ILevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EngineServiceImpl implements IEngineService {

    private static final String ENGINE_MESSAGE = "Engine";

    private final IEngineRepository engineRepository;

    private final ILevelService levelService;

    private final MapperService mapperService;

    @Autowired
    public EngineServiceImpl(IEngineRepository engineRepository, ILevelService levelService, MapperService mapperService) {
        this.engineRepository = engineRepository;
        this.levelService = levelService;
        this.mapperService = mapperService;
    }

    @Override
    @Cacheable(value = "engines", key = "#id")
    public EngineDto getEngine(Long id) {
        Engine engine = engineRepository.findByIdSpecific(id).orElseThrow(() -> new ResourceNotFoundException(ENGINE_MESSAGE, id));
        return mapperService.convertToDto(engine);
    }

    @Override
    @Transactional
    @CacheEvict(value = "allEngines", allEntries = true)
    public void createEngine(EngineDto engineDto) {
        verifyDataExists(engineDto);
        if (existsEngine(engineDto)) {
            throw new ResourceDuplicateException(ENGINE_MESSAGE, engineDto.getModel());
        }
        Engine engine = mapperService.convertToEntity(engineDto);
        engineRepository.save(engine);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"allEngines", "engines"}, allEntries = true)
    public void updateEngine(Long id, EngineDto engineDto) {
        verifyDataExists(engineDto);
        verifyExists(id);
        engineDto.setId(id);
        Engine engine = mapperService.convertToEntity(engineDto);
        engineRepository.save(engine);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"allEngines", "engines"}, allEntries = true)
    public void deleteEngine(Long id) {
        verifyExists(id);
        engineRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "allEngines")
    public List<EngineDto> getAllEngines() {
        return engineRepository.findAll().stream().map(mapperService::convertToDto).toList();
    }

    private void verifyDataExists(EngineDto engineDto) {
        LevelDto levelDto = levelService.getLevel(engineDto.getLevelDto().getId());
        if (!levelDto.equals(engineDto.getLevelDto())) {
            throw new DataNotFoundException(engineDto.getLevelDto().getLevel());
        }
    }

    private void verifyExists(Long id) {
        if (!engineRepository.existsById(id)) {
            throw new ResourceNotFoundException(ENGINE_MESSAGE, id);
        }
    }

    public boolean existsEngine(EngineDto engineDto) {
        return engineRepository.existsEngine(engineDto.getBhp(), engineDto.getLiters(), engineDto.getModel(), engineDto.getLevelDto().getId());
    }
}
