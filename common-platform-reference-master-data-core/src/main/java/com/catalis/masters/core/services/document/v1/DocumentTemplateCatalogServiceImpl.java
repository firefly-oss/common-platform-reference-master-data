package com.catalis.masters.core.services.document.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.masters.core.mappers.document.v1.DocumentTemplateCatalogMapper;
import com.catalis.masters.interfaces.dtos.document.v1.DocumentTemplateCatalogDTO;
import com.catalis.masters.models.entities.document.v1.DocumentTemplateCatalog;
import com.catalis.masters.models.repositories.document.v1.DocumentTemplateCatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

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
    public Mono<PaginationResponse<DocumentTemplateCatalogDTO>> listDocumentTemplatesByTypeId(Long typeId, PaginationRequest paginationRequest) {
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
    public Mono<DocumentTemplateCatalogDTO> getDocumentTemplate(Long templateId) {
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
    public Mono<DocumentTemplateCatalogDTO> updateDocumentTemplate(Long templateId, DocumentTemplateCatalogDTO documentTemplateDTO) {
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
    public Mono<Void> deleteDocumentTemplate(Long templateId) {
        return repository.findById(templateId)
                .switchIfEmpty(Mono.error(new RuntimeException("Document template not found with ID: " + templateId)))
                .flatMap(repository::delete)
                .onErrorResume(e -> Mono.error(new RuntimeException("Error deleting document template: " + e.getMessage(), e)));
    }
}
