package com.firefly.masters.core.services.contractdocumenttype.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.masters.interfaces.dtos.contractdocumenttype.v1.ContractDocumentTypeDTO;
import reactor.core.publisher.Mono;
import java.util.UUID;

/**
 * Service interface for managing Contract Document Type data.
 */
public interface ContractDocumentTypeService {

    /**
     * Retrieves a paginated list of contract document types based on the provided pagination request.
     *
     * @param paginationRequest the pagination request containing page number, size, and sorting options
     * @return a Mono emitting a PaginationResponse containing a list of ContractDocumentTypeDTO objects
     */
    Mono<PaginationResponse<ContractDocumentTypeDTO>> listContractDocumentTypes(PaginationRequest paginationRequest);

    /**
     * Creates a new contract document type record based on the provided ContractDocumentTypeDTO.
     *
     * @param contractDocumentTypeDto the DTO containing details of the contract document type to be created
     * @return a Mono emitting the created ContractDocumentTypeDTO object
     */
    Mono<ContractDocumentTypeDTO> createContractDocumentType(ContractDocumentTypeDTO contractDocumentTypeDto);

    /**
     * Retrieves the details of a contract document type by its unique identifier.
     *
     * @param documentTypeId the unique identifier of the contract document type to retrieve
     * @return a Mono emitting the ContractDocumentTypeDTO containing details about the specified contract document type, or an empty Mono if not found
     */
    Mono<ContractDocumentTypeDTO> getContractDocumentType(UUID documentTypeId);

    /**
     * Retrieves the details of a contract document type by its document code.
     *
     * @param documentCode the unique code of the contract document type to retrieve
     * @return a Mono emitting the ContractDocumentTypeDTO containing details about the specified contract document type, or an empty Mono if not found
     */
    Mono<ContractDocumentTypeDTO> getContractDocumentTypeByCode(String documentCode);

    /**
     * Updates an existing contract document type record based on the provided ContractDocumentTypeDTO.
     *
     * @param documentTypeId the unique identifier of the contract document type to update
     * @param contractDocumentTypeDto the DTO containing updated details of the contract document type
     * @return a Mono emitting the updated ContractDocumentTypeDTO object
     */
    Mono<ContractDocumentTypeDTO> updateContractDocumentType(UUID documentTypeId, ContractDocumentTypeDTO contractDocumentTypeDto);

    /**
     * Deletes a contract document type record by its unique identifier.
     *
     * @param documentTypeId the unique identifier of the contract document type to delete
     * @return a Mono signaling completion of the delete operation
     */
    Mono<Void> deleteContractDocumentType(UUID documentTypeId);
}
