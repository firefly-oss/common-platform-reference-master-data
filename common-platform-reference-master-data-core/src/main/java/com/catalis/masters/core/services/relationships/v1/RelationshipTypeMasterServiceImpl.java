package com.catalis.masters.core.services.relationships.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.masters.core.mappers.relationships.v1.RelationshipTypeMasterMapper;
import com.catalis.masters.interfaces.dtos.relationships.v1.RelationshipTypeMasterDTO;
import com.catalis.masters.models.entities.relationships.v1.RelationshipTypeMaster;
import com.catalis.masters.models.repositories.relationships.v1.RelationshipTypeMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@Transactional
public class RelationshipTypeMasterServiceImpl implements RelationshipTypeMasterService {

    @Autowired
    private RelationshipTypeMasterRepository repository;

    @Autowired
    private RelationshipTypeMasterMapper mapper;

    @Override
    public Mono<PaginationResponse<RelationshipTypeMasterDTO>> listRelationshipTypes(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findAllBy(pageable),
                () -> repository.count()
        );
    }

    @Override
    public Mono<RelationshipTypeMasterDTO> createRelationshipType(RelationshipTypeMasterDTO relationshipTypeDto) {
        RelationshipTypeMaster domain = mapper.toEntity(relationshipTypeDto);
        return repository.save(domain)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<RelationshipTypeMasterDTO> getRelationshipType(Long relationshipTypeId) {
        return repository.findById(relationshipTypeId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<RelationshipTypeMasterDTO> updateRelationshipType(Long relationshipTypeId, RelationshipTypeMasterDTO relationshipTypeDto) {
        return repository.findById(relationshipTypeId)
                .flatMap(foundRelationshipTypeMaster -> {
                    RelationshipTypeMaster updatedRelationshipType = mapper.toEntity(relationshipTypeDto);
                    updatedRelationshipType.setRelationshipTypeId(relationshipTypeId); // Preserving original id
                    updatedRelationshipType.setDateUpdated(LocalDateTime.now());
                    return repository.save(updatedRelationshipType);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteRelationshipType(Long relationshipTypeId) {
        return repository.findById(relationshipTypeId)
                .flatMap(repository::delete);
    }
}
