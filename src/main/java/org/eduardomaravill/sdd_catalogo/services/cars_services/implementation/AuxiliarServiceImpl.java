package org.eduardomaravill.sdd_catalogo.services.cars_services.implementation;

import jakarta.transaction.Transactional;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.AuxiliarDto;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.LevelDto;
import org.eduardomaravill.sdd_catalogo.exceptions.DataNotFoundException;
import org.eduardomaravill.sdd_catalogo.exceptions.ResourceDuplicateException;
import org.eduardomaravill.sdd_catalogo.exceptions.ResourceNotFoundException;
import org.eduardomaravill.sdd_catalogo.models.cars_models.Auxiliary;
import org.eduardomaravill.sdd_catalogo.repositories.cars_repositories.IAuxiliarRepository;
import org.eduardomaravill.sdd_catalogo.services.MapperService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.IAuxiliarService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.ILevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuxiliarServiceImpl implements IAuxiliarService {

    private static final String AUXILIAR_MESSAGE = "Auxiliar";

    private final IAuxiliarRepository auxiliarRepository;

    private final ILevelService levelService;

    private final MapperService mapperService;

    @Autowired
    public AuxiliarServiceImpl(IAuxiliarRepository auxiliarRepository, ILevelService levelService, MapperService mapperService) {
        this.auxiliarRepository = auxiliarRepository;
        this.levelService = levelService;
        this.mapperService = mapperService;
    }

    @Override
    @Cacheable(value = "auxiliaries", key = "#id")
    public AuxiliarDto getAuxiliar(Long id) {
        Auxiliary auxiliary = auxiliarRepository.findByIdSpecific(id).orElseThrow(()->new ResourceNotFoundException(AUXILIAR_MESSAGE,id));
        return mapperService.convertToDto(auxiliary);
    }

    @Override
    @Transactional
    @CacheEvict(value = "allAuxiliaries", allEntries = true)
    public void createAuxiliar(AuxiliarDto auxiliarDto) {
        verifyDataExists(auxiliarDto);
        if (auxiliarRepository.existsByAuxiliar(auxiliarDto.getAuxiliar())){
            throw new ResourceDuplicateException(AUXILIAR_MESSAGE, auxiliarDto.getAuxiliar());
        }
        Auxiliary auxiliary = mapperService.convertToEntity(auxiliarDto);
        auxiliarRepository.save(auxiliary);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"allAuxiliaries", "auxiliaries"}, allEntries = true)
    public void updateAuxiliar(Long id, AuxiliarDto auxiliarDto) {
        verifyExists(id);
        verifyDataExists(auxiliarDto);
        auxiliarDto.setId(id);
        Auxiliary auxiliary = mapperService.convertToEntity(auxiliarDto);
        auxiliarRepository.save(auxiliary);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"allAuxiliaries", "auxiliaries"}, allEntries = true)
    public void deleteAuxiliar(Long id) {
        verifyExists(id);
        auxiliarRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "allAuxiliaries")
    public List<AuxiliarDto> getAllAuxiliaries() {
        return auxiliarRepository.findAll().stream().map(mapperService::convertToDto).toList();
    }

    private void verifyDataExists(AuxiliarDto auxiliarDto){
        LevelDto levelDto = levelService.getLevel(auxiliarDto.getLevelDto().getId());
        if (!levelDto.equals(auxiliarDto.getLevelDto())) {
            throw new DataNotFoundException(auxiliarDto.getLevelDto().getLevel());
        }
    }

    private void verifyExists(Long id){
        if (!auxiliarRepository.existsById(id)){
            throw new ResourceNotFoundException(AUXILIAR_MESSAGE,id);
        }
    }
}
