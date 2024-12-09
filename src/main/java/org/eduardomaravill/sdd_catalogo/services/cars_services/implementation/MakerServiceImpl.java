package org.eduardomaravill.sdd_catalogo.services.cars_services.implementation;

import jakarta.transaction.Transactional;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.MakerDto;
import org.eduardomaravill.sdd_catalogo.exceptions.ResourceDuplicateException;
import org.eduardomaravill.sdd_catalogo.exceptions.ResourceNotFoundException;
import org.eduardomaravill.sdd_catalogo.models.cars_models.Maker;
import org.eduardomaravill.sdd_catalogo.repositories.cars_repositories.IMakersRepository;
import org.eduardomaravill.sdd_catalogo.services.MapperService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.IMakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MakerServiceImpl implements IMakerService {

    private static final String MAKER_MESSAGE = "Maker";

    private final IMakersRepository makersRepository;

    private final MapperService mapperService;

    @Autowired
    public MakerServiceImpl(IMakersRepository makersRepository, MapperService mapperService) {
        this.makersRepository = makersRepository;
        this.mapperService = mapperService;
    }

    @Override
    @Cacheable(value = "makers", key = "#id")
    public MakerDto getMaker(Long id) {
        Maker maker = makersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MAKER_MESSAGE, id));
        return mapperService.convertToDto(maker);
    }

    @Override
    @Transactional
    @CacheEvict(value = "allMakers", allEntries = true)
    public void createMaker(MakerDto makerDto) {
        if (makersRepository.existsByName(makerDto.getName())) {
            throw new ResourceDuplicateException(MAKER_MESSAGE, makerDto.getName());
        }
        Maker maker = mapperService.convertToEntity(makerDto);
        makersRepository.save(maker);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"allMakers", "makers"}, allEntries = true)
    public void updateMaker(Long id, MakerDto makerDto) {
        verifyExists(id);
        makerDto.setId(id);
        Maker maker = mapperService.convertToEntity(makerDto);
        makersRepository.save(maker);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"allMakers", "makers"}, allEntries = true)
    public void deleteMaker(Long id) {
        verifyExists(id);
        makersRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "allMakers")
    public List<MakerDto> getAllMakers() {
        return makersRepository.findAll().stream().map(mapperService::convertToDto).toList();
    }

    private void verifyExists(Long id) {
        if (!makersRepository.existsById(id)) {
            throw new ResourceNotFoundException(MAKER_MESSAGE, id);
        }
    }
}