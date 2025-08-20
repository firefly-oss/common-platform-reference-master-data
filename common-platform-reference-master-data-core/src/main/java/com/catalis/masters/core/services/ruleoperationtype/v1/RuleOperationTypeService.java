package com.catalis.masters.core.services.ruleoperationtype.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.masters.interfaces.dtos.ruleoperationtype.v1.RuleOperationTypeDTO;
import reactor.core.publisher.Mono;

/**
 * Service interface for managing Rule Operation Type data.
 */
public interface RuleOperationTypeService {

    /**
     * Retrieves a paginated list of rule operation types based on the provided filter request.
     *
     * @param filterRequest the filter request containing filters, page number, size, and sorting options
     * @return a Mono emitting a PaginationResponse containing a list of RuleOperationTypeDTO objects
     */
    Mono<PaginationResponse<RuleOperationTypeDTO>> listRuleOperationTypes(FilterRequest<RuleOperationTypeDTO> filterRequest);

    /**
     * Creates a new rule operation type record based on the provided RuleOperationTypeDTO.
     *
     * @param dto the DTO containing details of the rule operation type to be created
     * @return a Mono emitting the created RuleOperationTypeDTO object
     */
    Mono<RuleOperationTypeDTO> createRuleOperationType(RuleOperationTypeDTO dto);

    /**
     * Retrieves the details of a rule operation type by its unique identifier.
     *
     * @param operationTypeId the unique identifier of the rule operation type to retrieve
     * @return a Mono emitting the RuleOperationTypeDTO, or an empty Mono if not found
     */
    Mono<RuleOperationTypeDTO> getRuleOperationType(Long operationTypeId);

    /**
     * Updates the details of an existing rule operation type by its unique identifier.
     *
     * @param operationTypeId the unique identifier of the rule operation type to be updated
     * @param dto the DTO containing the updated rule operation type details
     * @return a Mono emitting the updated RuleOperationTypeDTO object
     */
    Mono<RuleOperationTypeDTO> updateRuleOperationType(Long operationTypeId, RuleOperationTypeDTO dto);

    /**
     * Deletes a rule operation type identified by its unique identifier.
     *
     * @param operationTypeId the unique identifier of the rule operation type to delete
     * @return a Mono signaling completion of the delete operation
     */
    Mono<Void> deleteRuleOperationType(Long operationTypeId);
}
