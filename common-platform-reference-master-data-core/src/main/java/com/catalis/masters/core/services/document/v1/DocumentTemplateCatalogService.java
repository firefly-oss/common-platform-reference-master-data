package com.catalis.masters.core.services.document.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.masters.interfaces.dtos.document.v1.DocumentTemplateCatalogDTO;
import reactor.core.publisher.Mono;

/**
 * Service interface for managing document template catalog operations.
 */
public interface DocumentTemplateCatalogService {

    /**
     * List all document templates with pagination.
     *
     * @param paginationRequest pagination parameters
     * @return a paginated response of document template DTOs
     */
    Mono<PaginationResponse<DocumentTemplateCatalogDTO>> listDocumentTemplates(PaginationRequest paginationRequest);

    /**
     * List document templates by category with pagination.
     *
     * @param category the category of templates to list
     * @param paginationRequest pagination parameters
     * @return a paginated response of document template DTOs
     */
    Mono<PaginationResponse<DocumentTemplateCatalogDTO>> listDocumentTemplatesByCategory(String category, PaginationRequest paginationRequest);

    /**
     * List document templates by type ID with pagination.
     *
     * @param typeId the type ID of templates to list
     * @param paginationRequest pagination parameters
     * @return a paginated response of document template DTOs
     */
    Mono<PaginationResponse<DocumentTemplateCatalogDTO>> listDocumentTemplatesByTypeId(Long typeId, PaginationRequest paginationRequest);

    /**
     * Create a new document template.
     *
     * @param documentTemplateDTO the document template data
     * @return the created document template DTO
     */
    Mono<DocumentTemplateCatalogDTO> createDocumentTemplate(DocumentTemplateCatalogDTO documentTemplateDTO);

    /**
     * Get a document template by ID.
     *
     * @param templateId the ID of the document template
     * @return the document template DTO
     */
    Mono<DocumentTemplateCatalogDTO> getDocumentTemplate(Long templateId);

    /**
     * Get a document template by code.
     *
     * @param templateCode the code of the document template
     * @return the document template DTO
     */
    Mono<DocumentTemplateCatalogDTO> getDocumentTemplateByCode(String templateCode);

    /**
     * Update a document template.
     *
     * @param templateId the ID of the document template to update
     * @param documentTemplateDTO the updated document template data
     * @return the updated document template DTO
     */
    Mono<DocumentTemplateCatalogDTO> updateDocumentTemplate(Long templateId, DocumentTemplateCatalogDTO documentTemplateDTO);

    /**
     * Delete a document template.
     *
     * @param templateId the ID of the document template to delete
     * @return a Mono of Void
     */
    Mono<Void> deleteDocumentTemplate(Long templateId);
}
