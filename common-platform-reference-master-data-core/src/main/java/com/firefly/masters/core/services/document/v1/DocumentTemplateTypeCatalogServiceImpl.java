package com.firefly.masters.core.services.document.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.masters.core.mappers.document.v1.DocumentTemplateTypeCatalogMapper;
import com.firefly.masters.interfaces.dtos.document.v1.DocumentTemplateTypeCatalogDTO;
import com.firefly.masters.models.entities.document.v1.DocumentTemplateTypeCatalog;
import com.firefly.masters.models.repositories.document.v1.DocumentTemplateTypeCatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Implementation of the DocumentTemplateTypeCatalogService interface.
 */
@Service
@Transactional
public class DocumentTemplateTypeCatalogServiceImpl implements DocumentTemplateTypeCatalogService {

    @Autowired
    private DocumentTemplateTypeCatalogRepository repository;

    @Autowired
    private DocumentTemplateTypeCatalogMapper mapper;

    @Override
    public Mono<PaginationResponse<DocumentTemplateTypeCatalogDTO>> listDocumentTemplateTypes(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findAllBy(pageable),
                () -> repository.count()
        );
    }

    @Override
    public Mono<DocumentTemplateTypeCatalogDTO> createDocumentTemplateType(DocumentTemplateTypeCatalogDTO documentTemplateTypeDTO) {
        // Set audit fields
        LocalDateTime now = LocalDateTime.now();
        documentTemplateTypeDTO.setDateCreated(now);
        documentTemplateTypeDTO.setDateUpdated(now);

        return Mono.just(documentTemplateTypeDTO)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO)
                .onErrorResume(e -> Mono.error(new RuntimeException("Error creating document template type: " + e.getMessage(), e)));
    }

    @Override
    public Mono<DocumentTemplateTypeCatalogDTO> getDocumentTemplateType(UUID typeId) {
        return repository.findById(typeId)
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.error(new RuntimeException("Document template type not found with ID: " + typeId)));
    }

    @Override
    public Mono<DocumentTemplateTypeCatalogDTO> getDocumentTemplateTypeByCode(String typeCode) {
        return repository.findByTypeCode(typeCode)
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.error(new RuntimeException("Document template type not found with code: " + typeCode)));
    }

    @Override
    public Mono<DocumentTemplateTypeCatalogDTO> updateDocumentTemplateType(UUID typeId, DocumentTemplateTypeCatalogDTO documentTemplateTypeDTO) {
        return repository.findById(typeId)
                .switchIfEmpty(Mono.error(new RuntimeException("Document template type not found with ID: " + typeId)))
                .flatMap(existingEntity -> {
                    DocumentTemplateTypeCatalog updatedEntity = mapper.toEntity(documentTemplateTypeDTO);
                    updatedEntity.setTypeId(typeId);
                    updatedEntity.setDateCreated(existingEntity.getDateCreated());
                    updatedEntity.setDateUpdated(LocalDateTime.now());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO)
                .onErrorResume(e -> Mono.error(new RuntimeException("Error updating document template type: " + e.getMessage(), e)));
    }

    @Override
    public Mono<Void> deleteDocumentTemplateType(UUID typeId) {
        return repository.findById(typeId)
                .switchIfEmpty(Mono.error(new RuntimeException("Document template type not found with ID: " + typeId)))
                .flatMap(repository::delete)
                .onErrorResume(e -> Mono.error(new RuntimeException("Error deleting document template type: " + e.getMessage(), e)));
    }
}
