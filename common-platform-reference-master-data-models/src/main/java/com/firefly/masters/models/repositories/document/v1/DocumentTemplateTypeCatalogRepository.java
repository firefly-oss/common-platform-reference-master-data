package com.firefly.masters.models.repositories.document.v1;

import com.firefly.masters.models.entities.document.v1.DocumentTemplateTypeCatalog;
import com.firefly.masters.models.repositories.BaseRepository;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Repository for managing DocumentTemplateTypeCatalog entities.
 * Extends BaseRepository to inherit common CRUD operations.
 */
@Repository
public interface DocumentTemplateTypeCatalogRepository extends BaseRepository<DocumentTemplateTypeCatalog, Long> {
    
    /**
     * Find a document template type by its code.
     *
     * @param typeCode the unique code of the document template type
     * @return a Mono of DocumentTemplateTypeCatalog
     */
    Mono<DocumentTemplateTypeCatalog> findByTypeCode(String typeCode);
    
    /**
     * Find a document template type by its name.
     *
     * @param typeName the name of the document template type
     * @return a Mono of DocumentTemplateTypeCatalog
     */
    Mono<DocumentTemplateTypeCatalog> findByTypeName(String typeName);
    
    /**
     * Count document template types by status.
     *
     * @param status the status to count
     * @return a Mono of Long representing the count
     */
    @Query("SELECT COUNT(*) FROM document_template_type_catalog WHERE status = :status")
    Mono<Long> countByStatus(String status);
}
