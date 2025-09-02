package com.firefly.masters.core.services.locale.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.masters.interfaces.dtos.locale.v1.LanguageLocaleDTO;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface LanguageLocaleService {

    /**
     * Lists language locales with pagination support.
     *
     * @param paginationRequest the details of the pagination request, such as page size and page number
     * @return a Mono emitting a PaginationResponse containing a list of LanguageLocaleDTO objects and metadata for pagination
     */
    Mono<PaginationResponse<LanguageLocaleDTO>> listLanguageLocales(PaginationRequest paginationRequest);

    /**
     * Creates a new language locale entry.
     *
     * @param dto the LanguageLocaleDTO object containing the details of the language locale to be created
     * @return a Mono emitting the created LanguageLocaleDTO object
     */
    Mono<LanguageLocaleDTO> createLanguageLocale(LanguageLocaleDTO dto);

    /**
     * Retrieves a language locale by its unique identifier.
     *
     * @param id the unique identifier of the language locale to retrieve
     * @return a Mono emitting the LanguageLocaleDTO corresponding to the given identifier, or an empty Mono if not found
     */
    Mono<LanguageLocaleDTO> getLanguageLocale(UUID id);

    /**
     * Updates an existing language locale with the provided details.
     *
     * @param id   the unique identifier of the language locale to update
     * @param dto  the data transfer object containing updated language locale details
     * @return a Mono emitting the updated LanguageLocaleDTO or an error if the update fails
     */
    Mono<LanguageLocaleDTO> updateLanguageLocale(UUID id, LanguageLocaleDTO dto);

    /**
     * Deletes a language locale identified by the specified ID.
     *
     * @param id the unique identifier of the language locale to be deleted
     * @return a Mono that completes when the operation is finished
     */
    Mono<Void> deleteLanguageLocale(UUID id);
}