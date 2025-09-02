package com.firefly.masters.core.services.identitydocument.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.masters.core.mappers.identitydocument.v1.IdentityDocumentCategoryCatalogMapper;
import com.firefly.masters.interfaces.dtos.identitydocument.v1.IdentityDocumentCategoryCatalogDTO;
import com.firefly.masters.models.entities.identitydocument.v1.IdentityDocumentCategoryCatalog;
import com.firefly.masters.models.repositories.identitydocument.v1.IdentityDocumentCategoryCatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Implementation of the IdentityDocumentCategoryCatalogService interface.
 */
@Service
@Transactional
public class IdentityDocumentCategoryCatalogServiceImpl implements IdentityDocumentCategoryCatalogService {

    @Autowired
    private IdentityDocumentCategoryCatalogRepository repository;

    @Autowired
    private IdentityDocumentCategoryCatalogMapper mapper;

    @Override
    public Mono<PaginationResponse<IdentityDocumentCategoryCatalogDTO>> listIdentityDocumentCategories(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findAllBy(pageable),
                () -> repository.count()
        );
    }

    @Override
    public Mono<IdentityDocumentCategoryCatalogDTO> createIdentityDocumentCategory(IdentityDocumentCategoryCatalogDTO identityDocumentCategoryDTO) {
        // Set audit fields
        LocalDateTime now = LocalDateTime.now();
        identityDocumentCategoryDTO.setDateCreated(now);
        identityDocumentCategoryDTO.setDateUpdated(now);

        return Mono.just(identityDocumentCategoryDTO)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO)
                .onErrorResume(e -> Mono.error(new RuntimeException("Error creating identity document category: " + e.getMessage(), e)));
    }

    @Override
    public Mono<IdentityDocumentCategoryCatalogDTO> getIdentityDocumentCategory(UUID categoryId) {
        return repository.findById(categoryId)
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.error(new RuntimeException("Identity document category not found with ID: " + categoryId)));
    }

    @Override
    public Mono<IdentityDocumentCategoryCatalogDTO> getIdentityDocumentCategoryByCode(String categoryCode) {
        return repository.findByCategoryCode(categoryCode)
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.error(new RuntimeException("Identity document category not found with code: " + categoryCode)));
    }

    @Override
    public Mono<IdentityDocumentCategoryCatalogDTO> updateIdentityDocumentCategory(UUID categoryId, IdentityDocumentCategoryCatalogDTO identityDocumentCategoryDTO) {
        return repository.findById(categoryId)
                .switchIfEmpty(Mono.error(new RuntimeException("Identity document category not found with ID: " + categoryId)))
                .flatMap(existingEntity -> {
                    IdentityDocumentCategoryCatalog updatedEntity = mapper.toEntity(identityDocumentCategoryDTO);
                    updatedEntity.setCategoryId(categoryId);
                    updatedEntity.setDateCreated(existingEntity.getDateCreated());
                    updatedEntity.setDateUpdated(LocalDateTime.now());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO)
                .onErrorResume(e -> Mono.error(new RuntimeException("Error updating identity document category: " + e.getMessage(), e)));
    }

    @Override
    public Mono<Void> deleteIdentityDocumentCategory(UUID categoryId) {
        return repository.findById(categoryId)
                .switchIfEmpty(Mono.error(new RuntimeException("Identity document category not found with ID: " + categoryId)))
                .flatMap(entity -> repository.deleteById(categoryId))
                .onErrorResume(e -> Mono.error(new RuntimeException("Error deleting identity document category: " + e.getMessage(), e)));
    }
}
