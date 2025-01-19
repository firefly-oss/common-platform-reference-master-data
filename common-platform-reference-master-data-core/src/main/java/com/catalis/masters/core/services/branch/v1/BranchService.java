package com.catalis.masters.core.services.branch.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.masters.interfaces.dtos.branch.v1.BranchDTO;
import reactor.core.publisher.Mono;

public interface BranchService {

    /**
     * Retrieves a paginated list of branches based on the provided pagination request.
     *
     * @param paginationRequest the request object containing pagination details such as page number and size.
     * @return a {@code Mono} that emits a {@code PaginationResponse} containing a list of {@code BranchDTO} objects.
     */
    Mono<PaginationResponse<BranchDTO>> listBranches(PaginationRequest paginationRequest);

    /**
     * Creates a new branch with the specified details.
     *
     * @param branchDto the details of the branch to create, including attributes such as branch code, name, address, city, province, country ID, phone number, and status
     * @return a {@code Mono} emitting the created {@code BranchDTO} instance containing the new branch details
     */
    Mono<BranchDTO> createBranch(BranchDTO branchDto);

    /**
     * Retrieves the branch details for a given branch ID.
     *
     * @param branchId the unique identifier of the branch to be retrieved
     * @return a Mono containing the BranchDTO object with the branch details, or an empty Mono if no branch is found
     */
    Mono<BranchDTO> getBranch(Long branchId);

    /**
     * Updates the details of an existing branch identified by its ID with the provided new data.
     *
     * @param branchId the unique identifier of the branch to be updated
     * @param branchDto the new data to update the branch with
     * @return a {@link Mono} emitting the updated {@link BranchDTO} object after the update is successful
     */
    Mono<BranchDTO> updateBranch(Long branchId, BranchDTO branchDto);

    /**
     * Deletes a branch based on the provided branch ID.
     *
     * @param branchId The unique identifier of the branch to be deleted.
     * @return A Mono that completes when the branch is successfully deleted.
     */
    Mono<Void> deleteBranch(Long branchId);
}
