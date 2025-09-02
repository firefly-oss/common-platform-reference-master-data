package com.firefly.masters.models.repositories.activity.v1;

import com.firefly.masters.models.entities.activity.v1.ActivityCode;
import com.firefly.masters.models.repositories.BaseRepository;
import reactor.core.publisher.Flux;
import java.util.UUID;

public interface ActivityCodeRepository extends BaseRepository<ActivityCode, UUID> {
    /**
     * Finds all activity codes for a specific country.
     *
     * @param countryId the unique identifier of the country to retrieve activity codes for
     * @return a Flux emitting ActivityCode objects for the specified country
     */
    Flux<ActivityCode> findByCountryId(UUID countryId);

    /**
     * Finds all child activity codes for a specific parent activity code.
     *
     * @param parentCodeId the unique identifier of the parent activity code
     * @return a Flux emitting ActivityCode objects that are children of the specified parent
     */
    Flux<ActivityCode> findByParentCodeId(UUID parentCodeId);
}
