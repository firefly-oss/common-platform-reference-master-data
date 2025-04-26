package com.catalis.masters.models.repositories.notification.v1;

import com.catalis.masters.models.entities.notification.v1.MessageTypeCatalog;
import com.catalis.masters.models.repositories.BaseRepository;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Repository for managing MessageTypeCatalog entities.
 * Extends BaseRepository to inherit common CRUD operations.
 */
@Repository
public interface MessageTypeCatalogRepository extends BaseRepository<MessageTypeCatalog, Long> {
    
    /**
     * Find a message type by its code.
     *
     * @param typeCode the unique code of the message type
     * @return a Mono of MessageTypeCatalog
     */
    Mono<MessageTypeCatalog> findByTypeCode(String typeCode);
    
    /**
     * Find a message type by its name.
     *
     * @param typeName the name of the message type
     * @return a Mono of MessageTypeCatalog
     */
    Mono<MessageTypeCatalog> findByTypeName(String typeName);
    
    /**
     * Count message types by status.
     *
     * @param status the status to count
     * @return a Mono of Long representing the count
     */
    @Query("SELECT COUNT(*) FROM message_type_catalog WHERE status = :status")
    Mono<Long> countByStatus(String status);
}
