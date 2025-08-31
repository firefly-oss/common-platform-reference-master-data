package com.firefly.masters.models.repositories.contractdocumenttype.v1;

import com.firefly.masters.models.entities.contractdocumenttype.v1.ContractDocumentType;
import com.firefly.masters.models.repositories.BaseRepository;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Repository for managing ContractDocumentType entities.
 * Extends BaseRepository to inherit common CRUD operations.
 */
@Repository
public interface ContractDocumentTypeRepository extends BaseRepository<ContractDocumentType, Long> {

    /**
     * Find a contract document type by its code.
     *
     * @param documentCode the unique code of the contract document type
     * @return a Mono of ContractDocumentType
     */
    Mono<ContractDocumentType> findByDocumentCode(String documentCode);

    /**
     * Find a contract document type by its name.
     *
     * @param name the name of the contract document type
     * @return a Mono of ContractDocumentType
     */
    Mono<ContractDocumentType> findByName(String name);

    /**
     * Check if a contract document type exists by its code.
     *
     * @param documentCode the unique code of the contract document type
     * @return a Mono of Boolean indicating existence
     */
    @Query("SELECT COUNT(*) > 0 FROM contract_document_type WHERE document_code = :documentCode")
    Mono<Boolean> existsByDocumentCode(String documentCode);
}
