package com.catalis.masters.core.services.bank.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.masters.interfaces.dtos.bank.v1.BankInstitutionCodeDTO;
import reactor.core.publisher.Mono;

public interface BankInstitutionCodeService {

    /**
     * Retrieves a paginated list of bank institution codes based on the provided pagination request.
     *
     * @param paginationRequest the request object containing pagination details,
     *                          such as page number and size, for fetching the paginated list.
     * @return a {@link Mono} emitting a {@link PaginationResponse} that contains a list of
     *         {@link BankInstitutionCodeDTO} objects matching the criteria specified
     *         in the pagination request.
     */
    Mono<PaginationResponse<BankInstitutionCodeDTO>> listBankInstitutionCodes(PaginationRequest paginationRequest);

    /**
     * Creates a new bank institution code with the provided details.
     *
     * @param dto the details of the bank institution code to be created, including fields like bank name,
     *            SWIFT code, routing number, country ID, status, and SVG icon.
     * @return a {@link Mono} that emits the created {@link BankInstitutionCodeDTO} upon success.
     */
    Mono<BankInstitutionCodeDTO> createBankInstitutionCode(BankInstitutionCodeDTO dto);

    /**
     * Retrieves a bank institution code by its unique identifier.
     *
     * @param id the unique identifier of the bank institution code to retrieve
     * @return a {@code Mono} containing the {@code BankInstitutionCodeDTO} if found, or an empty {@code Mono} if not found
     */
    Mono<BankInstitutionCodeDTO> getBankInstitutionCode(Long id);

    /**
     * Updates the bank institution code corresponding to the specified identifier.
     *
     * @param id the unique identifier of the bank institution code to be updated
     * @param dto the data transfer object containing the updated details for the bank institution code
     * @return a Mono containing the updated BankInstitutionCodeDTO
     */
    Mono<BankInstitutionCodeDTO> updateBankInstitutionCode(Long id, BankInstitutionCodeDTO dto);

    /**
     * Deletes the bank institution code associated with the specified ID.
     *
     * @param id the unique identifier of the bank institution code to be deleted
     * @return a Mono that completes when the deletion operation is finished
     */
    Mono<Void> deleteBankInstitutionCode(Long id);
}
