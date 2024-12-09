package org.eduardomaravill.sdd_catalogo.services.cars_services.implementation;

import jakarta.transaction.Transactional;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.TurboTypeDto;
import org.eduardomaravill.sdd_catalogo.exceptions.ResourceDuplicateException;
import org.eduardomaravill.sdd_catalogo.exceptions.ResourceNotFoundException;
import org.eduardomaravill.sdd_catalogo.models.cars_models.TurboType;
import org.eduardomaravill.sdd_catalogo.repositories.cars_repositories.ITurboTypeRepository;
import org.eduardomaravill.sdd_catalogo.services.MapperService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.ITurboTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurboTypeServiceImpl implements ITurboTypeService {

    private static final String TURBO_TYPE_MESSAGE = "TurboType";

    private final ITurboTypeRepository turboTypeRepository;

    private final MapperService mapperService;

    @Autowired
    public TurboTypeServiceImpl(ITurboTypeRepository turboTypeRepository, MapperService mapperService) {
        this.turboTypeRepository = turboTypeRepository;
        this.mapperService = mapperService;
    }

    @Override
    @Cacheable(value = "turboTypes", key = "#id")
    public TurboTypeDto getTurboType(Long id) {
        TurboType turboType = turboTypeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(TURBO_TYPE_MESSAGE, id));
        return mapperService.convertToDto(turboType);
    }

    @Override
    @Transactional
    @CacheEvict(value = "allTurboTypes", allEntries = true)
    public void createTurboType(TurboTypeDto turboTypeDto) {
        if (turboTypeRepository.existsByName(turboTypeDto.getType())) {
            throw new ResourceDuplicateException(TURBO_TYPE_MESSAGE, turboTypeDto.getType());
        }
        TurboType turboType = mapperService.convertToEntity(turboTypeDto);
        turboTypeRepository.save(turboType);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"allTurboTypes", "turboTypes"}, allEntries = true)
    public void updateTurboType(Long id, TurboTypeDto turboTypeDto) {
        verifyExists(id);
        turboTypeDto.setId(id);
        TurboType turboType = mapperService.convertToEntity(turboTypeDto);
        turboTypeRepository.save(turboType);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"allTurboTypes", "turboTypes"}, allEntries = true)
    public void deleteTurboType(Long id) {
        verifyExists(id);
        turboTypeRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "allTurboTypes")
    public List<TurboTypeDto> getAllTurboTypes() {
        return turboTypeRepository.findAll().stream().map(mapperService::convertToDto).toList();
    }

    private void verifyExists(Long id) {
        if (!turboTypeRepository.existsById(id)) {
            throw new ResourceNotFoundException(TURBO_TYPE_MESSAGE, id);
        }
    }
}