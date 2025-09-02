package com.firefly.masters.core.services.contracttype.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.masters.interfaces.dtos.contracttype.v1.ContractTypeDTO;
import reactor.core.publisher.Mono;
import java.util.UUID;

/**
 * Service interface for managing Contract Type data.
 */
public interface ContractTypeService {

    /**
     * Retrieves a paginated list of contract types based on the provided pagination request.
     *
     * @param paginationRequest the pagination request containing page number, size, and sorting options
     * @return a Mono emitting a PaginationResponse containing a list of ContractTypeDTO objects
     */
    Mono<PaginationResponse<ContractTypeDTO>> listContractTypes(PaginationRequest paginationRequest);

    /**
     * Creates a new contract type record based on the provided ContractTypeDTO.
     *
     * @param contractTypeDto the DTO containing details of the contract type to be created
     * @return a Mono emitting the created ContractTypeDTO object
     */
    Mono<ContractTypeDTO> createContractType(ContractTypeDTO contractTypeDto);

    /**
     * Retrieves the details of a contract type by its unique identifier.
     *
     * @param contractId the unique identifier of the contract type to retrieve
     * @return a Mono emitting the ContractTypeDTO containing details about the specified contract type, or an empty Mono if not found
     */
    Mono<ContractTypeDTO> getContractType(UUID contractId);

    /**
     * Updates the details of an existing contract type by its unique identifier.
     *
     * @param contractId the unique identifier of the contract type to be updated
     * @param contractTypeDto the DTO containing the updated contract type details
     * @return a Mono emitting the updated ContractTypeDTO object if the update is successful
     */
    Mono<ContractTypeDTO> updateContractType(UUID contractId, ContractTypeDTO contractTypeDto);

    /**
     * Deletes a contract type identified by its unique identifier.
     *
     * @param contractId the unique identifier of the contract type to delete
     * @return a Mono signaling completion of the delete operation
     */
    Mono<Void> deleteContractType(UUID contractId);
}