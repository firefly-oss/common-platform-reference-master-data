package com.catalis.masters.core.services.currency.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.masters.interfaces.dtos.currency.v1.CurrencyDTO;
import reactor.core.publisher.Mono;

public interface CurrencyService {
    /**
     * Retrieves a paginated list of currencies.
     *
     * @param paginationRequest the pagination request containing page size, page number, and other pagination-related details
     * @return a Mono containing a paginated response of CurrencyDTO objects
     */
    Mono<PaginationResponse<CurrencyDTO>> listCurrencies(PaginationRequest paginationRequest);

    /**
     * Creates a new currency entry based on the provided CurrencyDTO.
     *
     * @param currencyDto the object containing the details of the currency to be created
     * @return a Mono that emits the created CurrencyDTO
     */
    Mono<CurrencyDTO> createCurrency(CurrencyDTO currencyDto);

    /**
     * Retrieves currency information based on the provided currency ID.
     *
     * @param currencyId the unique identifier of the currency to retrieve
     * @return a Mono containing the retrieved CurrencyDTO object
     */
    Mono<CurrencyDTO> getCurrency(Long currencyId);

    /**
     * Updates the details of an existing currency identified by the given currency ID.
     *
     * @param currencyId the unique identifier of the currency to be updated
     * @param currencyDto the updated information for the currency
     * @return a Mono emitting the updated CurrencyDTO after the operation is completed
     */
    Mono<CurrencyDTO> updateCurrency(Long currencyId, CurrencyDTO currencyDto);

    /**
     * Deletes a currency based on the provided currency ID.
     *
     * @param currencyId the unique identifier of the currency to be deleted
     * @return a Mono indicating the completion of the operation
     */
    Mono<Void> deleteCurrency(Long currencyId);
}