package com.catalis.masters.models.repositories.consent.v1;

import com.catalis.masters.models.entities.consent.v1.ConsentCatalog;
import com.catalis.masters.models.repositories.BaseRepository;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;

/**
 * Repository for managing ConsentCatalog entities.
 * Extends BaseRepository to inherit common CRUD operations.
 */
public interface ConsentCatalogRepository extends BaseRepository<ConsentCatalog, Long> {
    
    /**
     * Find all consent catalog entries of a specific type.
     *
     * @param consentType the type of consent
     * @param pageable pagination information
     * @return a Flux of ConsentCatalog entities of the specified type
     */
    Flux<ConsentCatalog> findByConsentType(String consentType, Pageable pageable);
}