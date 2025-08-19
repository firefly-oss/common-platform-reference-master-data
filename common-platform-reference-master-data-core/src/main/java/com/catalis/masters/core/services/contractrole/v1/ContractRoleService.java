package com.catalis.masters.core.services.contractrole.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.masters.interfaces.dtos.contractrole.v1.ContractRoleDTO;
import reactor.core.publisher.Mono;

/**
 * Service interface for managing Contract Role data.
 */
public interface ContractRoleService {

    /**
     * Retrieves a paginated list of contract roles based on the provided filter request.
     *
     * @param filterRequest the filter request containing filters, page number, size, and sorting options
     * @return a Mono emitting a PaginationResponse containing a list of ContractRoleDTO objects
     */
    Mono<PaginationResponse<ContractRoleDTO>> listContractRoles(FilterRequest<ContractRoleDTO> filterRequest);

    /**
     * Creates a new contract role record based on the provided ContractRoleDTO.
     *
     * @param contractRoleDto the DTO containing details of the contract role to be created
     * @return a Mono emitting the created ContractRoleDTO object
     */
    Mono<ContractRoleDTO> createContractRole(ContractRoleDTO contractRoleDto);

    /**
     * Retrieves the details of a contract role by its unique identifier.
     *
     * @param roleId the unique identifier of the contract role to retrieve
     * @return a Mono emitting the ContractRoleDTO containing details about the specified contract role, or an empty Mono if not found
     */
    Mono<ContractRoleDTO> getContractRole(Long roleId);

    /**
     * Updates the details of an existing contract role by its unique identifier.
     *
     * @param roleId the unique identifier of the contract role to be updated
     * @param contractRoleDto the DTO containing the updated contract role details
     * @return a Mono emitting the updated ContractRoleDTO object if the update is successful
     */
    Mono<ContractRoleDTO> updateContractRole(Long roleId, ContractRoleDTO contractRoleDto);

    /**
     * Deletes a contract role identified by its unique identifier.
     *
     * @param roleId the unique identifier of the contract role to delete
     * @return a Mono signaling completion of the delete operation
     */
    Mono<Void> deleteContractRole(Long roleId);
}
