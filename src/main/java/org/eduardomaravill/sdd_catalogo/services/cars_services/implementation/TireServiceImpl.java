package org.eduardomaravill.sdd_catalogo.services.cars_services.implementation;

import jakarta.transaction.Transactional;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.LevelDto;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.StreetTypeDto;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.TireDto;
import org.eduardomaravill.sdd_catalogo.exceptions.DataNotFoundException;
import org.eduardomaravill.sdd_catalogo.exceptions.ResourceDuplicateException;
import org.eduardomaravill.sdd_catalogo.exceptions.ResourceNotFoundException;
import org.eduardomaravill.sdd_catalogo.models.cars_models.Tire;
import org.eduardomaravill.sdd_catalogo.repositories.cars_repositories.ITireRepository;
import org.eduardomaravill.sdd_catalogo.services.MapperService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.ILevelService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.IStreetTypeService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.ITireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TireServiceImpl implements ITireService {

    private static final String TIRE_MESSAGE = "Tire";

    private final ITireRepository tireRepository;

    private final IStreetTypeService streetTypeService;

    private final ILevelService levelService;

    private final MapperService mapperService;

    @Autowired
    public TireServiceImpl(ITireRepository tireRepository, IStreetTypeService streetTypeService, ILevelService levelService, MapperService mapperService) {
        this.tireRepository = tireRepository;
        this.streetTypeService = streetTypeService;
        this.levelService = levelService;
        this.mapperService = mapperService;
    }

    @Override
    @Cacheable(value = "tires", key = "#id")
    public TireDto getTire(Long id) {
        Tire tire = tireRepository.findByIdSpecific(id).orElseThrow(() -> new ResourceNotFoundException(TIRE_MESSAGE,id));
        return mapperService.convertToDto(tire);
    }

    @Override
    @Transactional
    @CacheEvict(value = "allTires", allEntries = true)
    public void createTire(TireDto tireDto) {
        verifyDataExists(tireDto);
        if (tireRepository.existsTire(tireDto.getStreetTypeDto().getId(),tireDto.getLevelDto().getId())){
            throw new ResourceDuplicateException(TIRE_MESSAGE,"this");
        }
        Tire tire = mapperService.convertToEntity(tireDto);
        tireRepository.save(tire);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"allTires","tires"}, allEntries = true)
    public void updateTire(Long id, TireDto tireDto) {
        verifyExists(id);
        verifyDataExists(tireDto);
        tireDto.setId(id);
        Tire tire = mapperService.convertToEntity(tireDto);
        tireRepository.save(tire);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"allTires","tires"}, allEntries = true)
    public void deleteTire(Long id) {
        verifyExists(id);
        tireRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "allTires")
    public List<TireDto> getAllTires() {
        return tireRepository.findAll().stream().map(mapperService::convertToDto).toList();
    }

    private void verifyDataExists(TireDto tireDto) {
        StreetTypeDto streetTypeDto = streetTypeService.getStreetType(tireDto.getStreetTypeDto().getId());
        if (!streetTypeDto.equals(tireDto.getStreetTypeDto())) {
            throw new DataNotFoundException(tireDto.getStreetTypeDto().getStreetTypeVal());
        }
        LevelDto levelDto = levelService.getLevel(tireDto.getLevelDto().getId());
        if (!levelDto.equals(tireDto.getLevelDto())) {
            throw new DataNotFoundException(tireDto.getLevelDto().getLevel());
        }
    }

    private void verifyExists(Long id) {
        if (!tireRepository.existsById(id)) {
            throw new ResourceNotFoundException(TIRE_MESSAGE, id);
        }
    }
}
