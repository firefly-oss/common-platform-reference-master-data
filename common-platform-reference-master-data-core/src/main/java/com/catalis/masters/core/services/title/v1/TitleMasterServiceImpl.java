package com.catalis.masters.core.services.title.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.masters.core.mappers.title.v1.TitleMasterMapper;
import com.catalis.masters.interfaces.dtos.title.v1.TitleMasterDTO;
import com.catalis.masters.models.entities.relationships.v1.RelationshipTypeMaster;
import com.catalis.masters.models.entities.title.v1.TitleMaster;
import com.catalis.masters.models.repositories.title.v1.TitleMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@Transactional
public class TitleMasterServiceImpl implements TitleMasterService {

    @Autowired
    private TitleMasterRepository repository;

    @Autowired
    private TitleMasterMapper mapper;

    @Override
    public Mono<PaginationResponse<TitleMasterDTO>> listTitles(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findAllBy(pageable),
                () -> repository.count()
        );
    }

    @Override
    public Mono<TitleMasterDTO> createTitle(TitleMasterDTO titleDto) {
        TitleMaster entity = mapper.toEntity(titleDto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<TitleMasterDTO> getTitle(Long titleId) {
        return repository.findById(titleId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<TitleMasterDTO> updateTitle(Long titleId, TitleMasterDTO titleDto) {
        return repository.findById(titleId)
                .flatMap(title -> {
                    TitleMaster titleMaster = mapper.toEntity(titleDto);
                    titleMaster.setTitleId(titleId); // Preserve actual id
                    titleMaster.setDateUpdated(LocalDateTime.now()); // Update the field
                    return repository.save(titleMaster);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteTitle(Long titleId) {
        return repository.findById(titleId)
                .flatMap(repository::delete);
    }
}
