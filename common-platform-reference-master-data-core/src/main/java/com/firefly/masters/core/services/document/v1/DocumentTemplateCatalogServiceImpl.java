package com.firefly.masters.core.services.document.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.masters.core.mappers.document.v1.DocumentTemplateCatalogMapper;
import com.firefly.masters.interfaces.dtos.document.v1.DocumentTemplateCatalogDTO;
import com.firefly.masters.models.entities.document.v1.DocumentTemplateCatalog;
import com.firefly.masters.models.repositories.document.v1.DocumentTemplateCatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Implementation of the DocumentTemplateCatalogService interface.
 */
@Service
@Transactional
public class DocumentTemplateCatalogServiceImpl implements DocumentTemplateCatalogService {

    @Autowired
    private DocumentTemplateCatalogRepository repository;

    @Autowired
    private DocumentTemplateCatalogMapper mapper;

    @Override
    public Mono<PaginationResponse<DocumentTemplateCatalogDTO>> listDocumentTemplates(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findAllBy(pageable),
                () -> repository.count()
        );
    }

    @Override
    public Mono<PaginationResponse<DocumentTemplateCatalogDTO>> listDocumentTemplatesByCategory(String category, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findByCategory(category, pageable),
                () -> repository.countByCategory(category)
        );
    }

    @Override
    public Mono<PaginationResponse<DocumentTemplateCatalogDTO>> listDocumentTemplatesByTypeId(UUID typeId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findByTypeId(typeId, pageable),
                () -> repository.countByTypeId(typeId)
        );
    }

    @Override
    public Mono<DocumentTemplateCatalogDTO> createDocumentTemplate(DocumentTemplateCatalogDTO documentTemplateDTO) {
        // Set audit fields
        LocalDateTime now = LocalDateTime.now();
        documentTemplateDTO.setDateCreated(now);
        documentTemplateDTO.setDateUpdated(now);

        return Mono.just(documentTemplateDTO)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO)
                .onErrorResume(e -> Mono.error(new RuntimeException("Error creating document template: " + e.getMessage(), e)));
    }

    @Override
    public Mono<DocumentTemplateCatalogDTO> getDocumentTemplate(UUID templateId) {
        return repository.findById(templateId)
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.error(new RuntimeException("Document template not found with ID: " + templateId)));
    }

    @Override
    public Mono<DocumentTemplateCatalogDTO> getDocumentTemplateByCode(String templateCode) {
        return repository.findByTemplateCode(templateCode)
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.error(new RuntimeException("Document template not found with code: " + templateCode)));
    }

    @Override
    public Mono<DocumentTemplateCatalogDTO> updateDocumentTemplate(UUID templateId, DocumentTemplateCatalogDTO documentTemplateDTO) {
        return repository.findById(templateId)
                .switchIfEmpty(Mono.error(new RuntimeException("Document template not found with ID: " + templateId)))
                .flatMap(existingEntity -> {
                    DocumentTemplateCatalog updatedEntity = mapper.toEntity(documentTemplateDTO);
                    updatedEntity.setTemplateId(templateId);
                    updatedEntity.setDateCreated(existingEntity.getDateCreated());
                    updatedEntity.setDateUpdated(LocalDateTime.now());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO)
                .onErrorResume(e -> Mono.error(new RuntimeException("Error updating document template: " + e.getMessage(), e)));
    }

    @Override
    public Mono<Void> deleteDocumentTemplate(UUID templateId) {
        return repository.findById(templateId)
                .switchIfEmpty(Mono.error(new RuntimeException("Document template not found with ID: " + templateId)))
                .flatMap(repository::delete)
                .onErrorResume(e -> Mono.error(new RuntimeException("Error deleting document template: " + e.getMessage(), e)));
    }
}
