package com.catalis.masters.core.services.identitydocument.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.masters.core.mappers.identitydocument.v1.IdentityDocumentCatalogMapper;
import com.catalis.masters.interfaces.dtos.identitydocument.v1.IdentityDocumentCatalogDTO;
import com.catalis.masters.models.entities.identitydocument.v1.IdentityDocumentCatalog;
import com.catalis.masters.models.repositories.identitydocument.v1.IdentityDocumentCatalogRepository;
import com.catalis.masters.models.repositories.identitydocument.v1.IdentityDocumentLocalizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Implementation of the IdentityDocumentCatalogService interface.
 */
@Service
@Transactional
public class IdentityDocumentCatalogServiceImpl implements IdentityDocumentCatalogService {

    @Autowired
    private IdentityDocumentCatalogRepository repository;

    @Autowired
    private IdentityDocumentLocalizationRepository localizationRepository;

    @Autowired
    private IdentityDocumentCatalogMapper mapper;

    @Override
    public Mono<PaginationResponse<IdentityDocumentCatalogDTO>> listIdentityDocuments(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findAllBy(pageable),
                () -> repository.count()
        );
    }

    @Override
    public Mono<PaginationResponse<IdentityDocumentCatalogDTO>> listIdentityDocumentsByCategory(Long categoryId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findByCategoryId(categoryId, pageable),
                () -> repository.countByCategoryId(categoryId)
        );
    }

    @Override
    public Mono<PaginationResponse<IdentityDocumentCatalogDTO>> listIdentityDocumentsByCountry(Long countryId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findByCountryId(countryId, pageable),
                () -> repository.countByCountryId(countryId)
        );
    }

    @Override
    public Mono<IdentityDocumentCatalogDTO> createIdentityDocument(IdentityDocumentCatalogDTO identityDocumentDTO) {
        // Set audit fields
        LocalDateTime now = LocalDateTime.now();
        identityDocumentDTO.setDateCreated(now);
        identityDocumentDTO.setDateUpdated(now);

        return Mono.just(identityDocumentDTO)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO)
                .onErrorResume(e -> Mono.error(new RuntimeException("Error creating identity document: " + e.getMessage(), e)));
    }

    @Override
    public Mono<IdentityDocumentCatalogDTO> getIdentityDocument(Long documentId) {
        return repository.findById(documentId)
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.error(new RuntimeException("Identity document not found with ID: " + documentId)));
    }

    @Override
    public Mono<IdentityDocumentCatalogDTO> getIdentityDocumentByCode(String documentCode) {
        return repository.findByDocumentCode(documentCode)
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.error(new RuntimeException("Identity document not found with code: " + documentCode)));
    }

    @Override
    public Mono<IdentityDocumentCatalogDTO> updateIdentityDocument(Long documentId, IdentityDocumentCatalogDTO identityDocumentDTO) {
        return repository.findById(documentId)
                .switchIfEmpty(Mono.error(new RuntimeException("Identity document not found with ID: " + documentId)))
                .flatMap(existingEntity -> {
                    IdentityDocumentCatalog updatedEntity = mapper.toEntity(identityDocumentDTO);
                    updatedEntity.setDocumentId(documentId);
                    updatedEntity.setDateCreated(existingEntity.getDateCreated());
                    updatedEntity.setDateUpdated(LocalDateTime.now());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO)
                .onErrorResume(e -> Mono.error(new RuntimeException("Error updating identity document: " + e.getMessage(), e)));
    }

    @Override
    public Mono<Void> deleteIdentityDocument(Long documentId) {
        return repository.findById(documentId)
                .switchIfEmpty(Mono.error(new RuntimeException("Identity document not found with ID: " + documentId)))
                .flatMap(entity -> localizationRepository.deleteByDocumentId(documentId)
                        .then(repository.deleteById(documentId)))
                .onErrorResume(e -> Mono.error(new RuntimeException("Error deleting identity document: " + e.getMessage(), e)));
    }
}
