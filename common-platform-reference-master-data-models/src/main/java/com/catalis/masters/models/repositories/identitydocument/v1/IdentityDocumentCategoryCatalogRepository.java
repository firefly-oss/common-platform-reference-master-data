package com.catalis.masters.models.repositories.identitydocument.v1;

import com.catalis.masters.models.entities.identitydocument.v1.IdentityDocumentCategoryCatalog;
import com.catalis.masters.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Repository for managing IdentityDocumentCategoryCatalog entities.
 * Extends BaseRepository to inherit common CRUD operations.
 */
@Repository
public interface IdentityDocumentCategoryCatalogRepository extends BaseRepository<IdentityDocumentCategoryCatalog, Long> {
    
    /**
     * Find an identity document category by its code.
     *
     * @param categoryCode the unique code of the identity document category
     * @return a Mono of IdentityDocumentCategoryCatalog
     */
    Mono<IdentityDocumentCategoryCatalog> findByCategoryCode(String categoryCode);
    
    /**
     * Find an identity document category by its name.
     *
     * @param categoryName the name of the identity document category
     * @return a Mono of IdentityDocumentCategoryCatalog
     */
    Mono<IdentityDocumentCategoryCatalog> findByCategoryName(String categoryName);
}
