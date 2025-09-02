package com.firefly.masters.models.repositories.legal.v1;

import com.firefly.masters.models.entities.legal.v1.LegalForm;
import com.firefly.masters.models.repositories.BaseRepository;
import reactor.core.publisher.Flux;
import java.util.UUID;

public interface LegalFormRepository extends BaseRepository<LegalForm, UUID> {
    /**
     * Finds all legal forms for a specific country.
     *
     * @param countryId the unique identifier of the country to retrieve legal forms for
     * @return a Flux emitting LegalForm objects for the specified country
     */
    Flux<LegalForm> findByCountryId(UUID countryId);
}
