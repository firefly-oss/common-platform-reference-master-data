package com.firefly.masters.core.services.division.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.masters.core.mappers.division.v1.AdministrativeDivisionMapper;
import com.firefly.masters.interfaces.dtos.division.v1.AdministrativeDivisionDTO;
import com.firefly.masters.models.entities.division.v1.AdministrativeDivision;
import com.firefly.masters.models.repositories.division.v1.AdministrativeDivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Service
@Transactional
public class AdministrativeDivisionServiceImpl implements AdministrativeDivisionService {

    @Autowired
    private AdministrativeDivisionRepository repository;

    @Autowired
    private AdministrativeDivisionMapper mapper;

    @Override
    public Mono<PaginationResponse<AdministrativeDivisionDTO>> listDivisions(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findAllBy(pageable),
                () -> repository.count()
        );
    }

    @Override
    public Mono<AdministrativeDivisionDTO> createDivision(AdministrativeDivisionDTO divisionDto) {
        AdministrativeDivision division = mapper.toEntity(divisionDto);
        return repository.save(division)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<AdministrativeDivisionDTO> getDivision(UUID divisionId) {
        return repository.findById(divisionId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<AdministrativeDivisionDTO> updateDivision(UUID divisionId, AdministrativeDivisionDTO divisionDto) {
        return repository.findById(divisionId)
                .flatMap(foundDivision -> {
                    AdministrativeDivision updatedDivision = mapper.toEntity(divisionDto);
                    updatedDivision.setDivisionId(foundDivision.getDivisionId());
                    return repository.save(updatedDivision);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteDivision(UUID divisionId) {
        return repository.findById(divisionId)
                .flatMap(repository::delete);
    }
}