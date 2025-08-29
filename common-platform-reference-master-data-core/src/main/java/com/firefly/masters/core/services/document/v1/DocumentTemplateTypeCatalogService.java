package com.firefly.masters.core.services.document.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.masters.interfaces.dtos.document.v1.DocumentTemplateTypeCatalogDTO;
import reactor.core.publisher.Mono;

/**
 * Service interface for managing document template type catalog operations.
 */
public interface DocumentTemplateTypeCatalogService {

    /**
     * List all document template types with pagination.
     *
     * @param paginationRequest pagination parameters
     * @return a paginated response of document template type DTOs
     */
    Mono<PaginationResponse<DocumentTemplateTypeCatalogDTO>> listDocumentTemplateTypes(PaginationRequest paginationRequest);

    /**
     * Create a new document template type.
     *
     * @param documentTemplateTypeDTO the document template type data
     * @return the created document template type DTO
     */
    Mono<DocumentTemplateTypeCatalogDTO> createDocumentTemplateType(DocumentTemplateTypeCatalogDTO documentTemplateTypeDTO);

    /**
     * Get a document template type by ID.
     *
     * @param typeId the ID of the document template type
     * @return the document template type DTO
     */
    Mono<DocumentTemplateTypeCatalogDTO> getDocumentTemplateType(Long typeId);

    /**
     * Get a document template type by code.
     *
     * @param typeCode the code of the document template type
     * @return the document template type DTO
     */
    Mono<DocumentTemplateTypeCatalogDTO> getDocumentTemplateTypeByCode(String typeCode);

    /**
     * Update a document template type.
     *
     * @param typeId the ID of the document template type to update
     * @param documentTemplateTypeDTO the updated document template type data
     * @return the updated document template type DTO
     */
    Mono<DocumentTemplateTypeCatalogDTO> updateDocumentTemplateType(Long typeId, DocumentTemplateTypeCatalogDTO documentTemplateTypeDTO);

    /**
     * Delete a document template type.
     *
     * @param typeId the ID of the document template type to delete
     * @return a Mono of Void
     */
    Mono<Void> deleteDocumentTemplateType(Long typeId);
}
