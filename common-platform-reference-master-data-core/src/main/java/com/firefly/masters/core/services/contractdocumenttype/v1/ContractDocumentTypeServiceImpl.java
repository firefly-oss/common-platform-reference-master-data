package com.firefly.masters.core.services.contractdocumenttype.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.masters.core.mappers.contractdocumenttype.v1.ContractDocumentTypeMapper;
import com.firefly.masters.interfaces.dtos.contractdocumenttype.v1.ContractDocumentTypeDTO;
import com.firefly.masters.models.entities.contractdocumenttype.v1.ContractDocumentType;
import com.firefly.masters.models.repositories.contractdocumenttype.v1.ContractDocumentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class ContractDocumentTypeServiceImpl implements ContractDocumentTypeService {

    @Autowired
    private ContractDocumentTypeRepository repository;

    @Autowired
    private ContractDocumentTypeMapper mapper;

    @Override
    public Mono<PaginationResponse<ContractDocumentTypeDTO>> listContractDocumentTypes(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findAllBy(pageable),
                () -> repository.count()
        );
    }

    @Override
    public Mono<ContractDocumentTypeDTO> createContractDocumentType(ContractDocumentTypeDTO contractDocumentTypeDto) {
        ContractDocumentType entity = mapper.toEntity(contractDocumentTypeDto);
        entity.setDateCreated(LocalDateTime.now());
        entity.setDateUpdated(LocalDateTime.now());
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ContractDocumentTypeDTO> getContractDocumentType(UUID documentTypeId) {
        return repository.findById(documentTypeId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ContractDocumentTypeDTO> getContractDocumentTypeByCode(String documentCode) {
        return repository.findByDocumentCode(documentCode)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ContractDocumentTypeDTO> updateContractDocumentType(UUID documentTypeId, ContractDocumentTypeDTO contractDocumentTypeDto) {
        return repository.findById(documentTypeId)
                .flatMap(contractDocumentType -> {
                    ContractDocumentType updatedContractDocumentType = mapper.toEntity(contractDocumentTypeDto);
                    updatedContractDocumentType.setDocumentTypeId(documentTypeId); // Preserve actual id
                    updatedContractDocumentType.setDateCreated(contractDocumentType.getDateCreated()); // Preserve creation date
                    updatedContractDocumentType.setDateUpdated(LocalDateTime.now()); // Update the field
                    return repository.save(updatedContractDocumentType);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteContractDocumentType(UUID documentTypeId) {
        return repository.findById(documentTypeId)
                .flatMap(repository::delete);
    }
}
