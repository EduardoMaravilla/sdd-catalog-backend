package org.eduardomaravill.sdd_catalogo.services.cars_services.implementation;

import jakarta.transaction.Transactional;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.StreetTypeDto;
import org.eduardomaravill.sdd_catalogo.exceptions.ResourceDuplicateException;
import org.eduardomaravill.sdd_catalogo.exceptions.ResourceNotFoundException;
import org.eduardomaravill.sdd_catalogo.models.cars_models.StreetType;
import org.eduardomaravill.sdd_catalogo.repositories.cars_repositories.IStreetTypeRepository;
import org.eduardomaravill.sdd_catalogo.services.MapperService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.IStreetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreetTypeServiceImpl implements IStreetTypeService {

    private static final String STREET_TYPE_MESSAGE = "StreetType";

    private final IStreetTypeRepository streetTypeRepository;

    private final MapperService mapperService;

    @Autowired
    public StreetTypeServiceImpl(IStreetTypeRepository streetTypeRepository, MapperService mapperService) {
        this.streetTypeRepository = streetTypeRepository;
        this.mapperService = mapperService;
    }

    @Override
    @Cacheable(value = "streetTypes",key = "#id")
    public StreetTypeDto getStreetType(Long id) {
        StreetType streetType = streetTypeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(STREET_TYPE_MESSAGE,id));
        return mapperService.convertToDto(streetType);
    }

    @Override
    @Transactional
    @CacheEvict(value = "allStreetTypes",allEntries = true)
    public void createStreetType(StreetTypeDto streetTypeDto) {
        if (streetTypeRepository.existsByName(streetTypeDto.getStreetTypeVal())) {
            throw new ResourceDuplicateException(STREET_TYPE_MESSAGE, streetTypeDto.getStreetTypeVal());
        }
        StreetType streetType = mapperService.convertToEntity(streetTypeDto);
        streetTypeRepository.save(streetType);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"allStreetTypes","streetTypes"}, allEntries = true)
    public void updateStreetType(Long id, StreetTypeDto streetTypeDto) {
        verifyExists(id);
        StreetType streetType = mapperService.convertToEntity(streetTypeDto);
        streetTypeRepository.save(streetType);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"allStreetTypes","streetTypes"}, allEntries = true)
    public void deleteStreetType(Long id) {
      verifyExists(id);
      streetTypeRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "allStreetTypes")
    public List<StreetTypeDto> getAllStreetTypes() {
        return streetTypeRepository.findAll().stream().map(mapperService::convertToDto).toList();
    }

    private void verifyExists(Long id){
        if (!streetTypeRepository.existsById(id)) {
            throw new ResourceNotFoundException(STREET_TYPE_MESSAGE, id);
        }
    }
}