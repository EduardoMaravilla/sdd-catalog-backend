package org.eduardomaravill.sdd_catalogo.services.cars_services.implementation;

import jakarta.transaction.Transactional;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.LevelDto;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.StreetTypeDto;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.SuspensionDto;
import org.eduardomaravill.sdd_catalogo.exceptions.DataNotFoundException;
import org.eduardomaravill.sdd_catalogo.exceptions.ResourceDuplicateException;
import org.eduardomaravill.sdd_catalogo.exceptions.ResourceNotFoundException;
import org.eduardomaravill.sdd_catalogo.models.cars_models.Suspension;
import org.eduardomaravill.sdd_catalogo.repositories.cars_repositories.ISuspensionRepository;
import org.eduardomaravill.sdd_catalogo.services.MapperService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.ILevelService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.IStreetTypeService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.ISuspensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuspensionServiceImpl implements ISuspensionService {

    private static final String SUSPENSION_MESSAGE = "suspension";

    private final ISuspensionRepository suspensionRepository;

    private final IStreetTypeService streetTypeService;

    private final ILevelService levelService;

    private final MapperService mapperService;

    @Autowired
    public SuspensionServiceImpl(ISuspensionRepository suspensionRepository, IStreetTypeService streetTypeService, ILevelService levelService, MapperService mapperService) {
        this.suspensionRepository = suspensionRepository;
        this.streetTypeService = streetTypeService;
        this.levelService = levelService;
        this.mapperService = mapperService;
    }

    @Override
    @Cacheable(value = "suspensions", key = "#id")
    public SuspensionDto getSuspension(Long id) {
        Suspension suspension = suspensionRepository.findByIdSpecific(id).orElseThrow(() -> new ResourceNotFoundException(SUSPENSION_MESSAGE,id));
        return mapperService.convertToDto(suspension);
    }

    @Override
    @Transactional
    @CacheEvict(value = "allSuspensions", allEntries = true)
    public void createSuspension(SuspensionDto suspensionDto) {
        verifyDataExists(suspensionDto);
        if (suspensionRepository.existsSuspension(suspensionDto.getStreetTypeDto().getId(),suspensionDto.getLevelDto().getId())){
            throw new ResourceDuplicateException(SUSPENSION_MESSAGE,"this");
        }
        Suspension suspension = mapperService.convertToEntity(suspensionDto);
        suspensionRepository.save(suspension);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"allSuspensions","suspensions"},allEntries = true)
    public void updateSuspension(Long id, SuspensionDto suspensionDto) {
        verifyExists(id);
        verifyDataExists(suspensionDto);
        suspensionDto.setId(id);
        Suspension suspension = mapperService.convertToEntity(suspensionDto);
        suspensionRepository.save(suspension);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"allSuspensions","suspensions"},allEntries = true)
    public void deleteSuspension(Long id) {
        verifyExists(id);
        suspensionRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "allSuspensions")
    public List<SuspensionDto> getAllSuspensions() {
        return suspensionRepository.findAll().stream().map(mapperService::convertToDto).toList();
    }

    private void verifyDataExists(SuspensionDto suspensionDto) {
        StreetTypeDto streetTypeDto = streetTypeService.getStreetType(suspensionDto.getStreetTypeDto().getId());
        if (!streetTypeDto.equals(suspensionDto.getStreetTypeDto())) {
            throw new DataNotFoundException(suspensionDto.getStreetTypeDto().getStreetTypeVal());
        }
        LevelDto levelDto = levelService.getLevel(suspensionDto.getLevelDto().getId());
        if (!levelDto.equals(suspensionDto.getLevelDto())) {
            throw new DataNotFoundException(suspensionDto.getLevelDto().getLevel());
        }
    }

    private void verifyExists(Long id) {
        if (!suspensionRepository.existsById(id)) {
            throw new ResourceNotFoundException(SUSPENSION_MESSAGE, id);
        }
    }
}
