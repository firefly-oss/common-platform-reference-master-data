package com.firefly.masters.models.repositories.lookup.v1;

import com.firefly.masters.models.entities.lookup.v1.LookupItem;
import com.firefly.masters.models.repositories.BaseRepository;
import reactor.core.publisher.Flux;

public interface LookupItemRepository extends BaseRepository<LookupItem, Long> {
    /**
     * Finds all lookup items for a specific domain.
     *
     * @param domainId the unique identifier of the domain to retrieve items for
     * @return a Flux emitting LookupItem objects for the specified domain
     */
    Flux<LookupItem> findByDomainId(Long domainId);
}
