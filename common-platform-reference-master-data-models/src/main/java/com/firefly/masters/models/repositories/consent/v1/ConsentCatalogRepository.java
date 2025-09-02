package com.firefly.masters.models.repositories.consent.v1;

import com.firefly.masters.models.entities.consent.v1.ConsentCatalog;
import com.firefly.masters.models.repositories.BaseRepository;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import java.util.UUID;

/**
 * Repository for managing ConsentCatalog entities.
 * Extends BaseRepository to inherit common CRUD operations.
 */
public interface ConsentCatalogRepository extends BaseRepository<ConsentCatalog, UUID> {
    
    /**
     * Find all consent catalog entries of a specific type.
     *
     * @param consentType the type of consent
     * @param pageable pagination information
     * @return a Flux of ConsentCatalog entities of the specified type
     */
    Flux<ConsentCatalog> findByConsentType(String consentType, Pageable pageable);
}