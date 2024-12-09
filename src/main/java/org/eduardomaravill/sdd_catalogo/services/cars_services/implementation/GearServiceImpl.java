package org.eduardomaravill.sdd_catalogo.services.cars_services.implementation;

import jakarta.transaction.Transactional;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.GearDto;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.LevelDto;
import org.eduardomaravill.sdd_catalogo.exceptions.DataNotFoundException;
import org.eduardomaravill.sdd_catalogo.exceptions.ResourceDuplicateException;
import org.eduardomaravill.sdd_catalogo.exceptions.ResourceNotFoundException;
import org.eduardomaravill.sdd_catalogo.models.cars_models.Gear;
import org.eduardomaravill.sdd_catalogo.repositories.cars_repositories.IGearRepository;
import org.eduardomaravill.sdd_catalogo.services.MapperService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.IGearService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.ILevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GearServiceImpl implements IGearService {

    private static final String GEAR_MESSAGE = "Gear";

    private final IGearRepository gearRepository;

    private final ILevelService levelService;

    private final MapperService mapperService;

    @Autowired
    public GearServiceImpl(IGearRepository gearRepository, ILevelService levelService, MapperService mapperService) {
        this.gearRepository = gearRepository;
        this.levelService = levelService;
        this.mapperService = mapperService;
    }

    @Override
    @Cacheable(value = "gears", key = "#id")
    public GearDto getGear(Long id) {
        Gear gear = gearRepository.findByIdSpecific(id).orElseThrow(() -> new ResourceNotFoundException(GEAR_MESSAGE, id));
        return mapperService.convertToDto(gear);
    }

    @Override
    @Transactional
    @CacheEvict(value = "allGears", allEntries = true)
    public void createGear(GearDto gearDto) {
        verifyDataExists(gearDto);
        if (gearRepository.existsByGear(gearDto.getGearValue())) {
            throw new ResourceDuplicateException(GEAR_MESSAGE, Integer.toString(gearDto.getGearValue()));
        }
        Gear gear = mapperService.convertToEntity(gearDto);
        gearRepository.save(gear);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"allGears","gears"},allEntries = true)
    public void updateGear(Long id, GearDto gearDto) {
        verifyDataExists(gearDto);
        verifyExists(id);
        gearDto.setId(id);
        Gear gear = mapperService.convertToEntity(gearDto);
        gearRepository.save(gear);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"allGears","gears"},allEntries = true)
    public void deleteGear(Long id) {
        verifyExists(id);
        gearRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "allGears")
    public List<GearDto> getAllGears() {
        return gearRepository.findAll().stream().map(mapperService::convertToDto).toList();
    }

    private void verifyDataExists(GearDto gearDto) {
        LevelDto levelDto = levelService.getLevel(gearDto.getLevelDto().getId());
        if (!levelDto.equals(gearDto.getLevelDto())) {
            throw new DataNotFoundException(gearDto.getLevelDto().getLevel());
        }
    }

    private void verifyExists(Long id) {
        if (!gearRepository.existsById(id)) {
            throw new ResourceNotFoundException(GEAR_MESSAGE, id);
        }
    }
}
