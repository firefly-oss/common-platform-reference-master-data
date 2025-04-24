package com.catalis.masters.core.services.consent.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.masters.core.mappers.consent.v1.ConsentCatalogMapper;
import com.catalis.masters.interfaces.dtos.consent.v1.ConsentCatalogDTO;
import com.catalis.masters.models.entities.consent.v1.ConsentCatalog;
import com.catalis.masters.models.repositories.consent.v1.ConsentCatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Implementation of the ConsentCatalogService interface.
 */
@Service
@Transactional
public class ConsentCatalogServiceImpl implements ConsentCatalogService {

    @Autowired
    private ConsentCatalogRepository repository;

    @Autowired
    private ConsentCatalogMapper mapper;

    @Override
    public Mono<PaginationResponse<ConsentCatalogDTO>> listConsentCatalog(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findAllBy(pageable),
                () -> repository.count()
        );
    }

    @Override
    public Mono<PaginationResponse<ConsentCatalogDTO>> listConsentCatalogByType(String consentType, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findByConsentType(consentType, pageable),
                () -> repository.count()
        );
    }

    @Override
    public Mono<ConsentCatalogDTO> createConsentCatalog(ConsentCatalogDTO dto) {
        // Set audit fields
        LocalDateTime now = LocalDateTime.now();
        dto.setDateCreated(now);
        dto.setDateUpdated(now);

        ConsentCatalog entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ConsentCatalogDTO> getConsentCatalog(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ConsentCatalogDTO> updateConsentCatalog(Long id, ConsentCatalogDTO dto) {
        return repository.findById(id)
                .flatMap(existingEntity -> {
                    // Update audit fields
                    dto.setDateUpdated(LocalDateTime.now());
                    dto.setDateCreated(existingEntity.getDateCreated());

                    ConsentCatalog updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setConsentId(existingEntity.getConsentId());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteConsentCatalog(Long id) {
        return repository.deleteById(id);
    }
}