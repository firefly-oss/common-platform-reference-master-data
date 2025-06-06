package com.catalis.masters.core.services.contracttype.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.masters.core.mappers.contracttype.v1.ContractTypeMapper;
import com.catalis.masters.interfaces.dtos.contracttype.v1.ContractTypeDTO;
import com.catalis.masters.models.entities.contracttype.v1.ContractType;
import com.catalis.masters.models.repositories.contracttype.v1.ContractTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@Transactional
public class ContractTypeServiceImpl implements ContractTypeService {

    @Autowired
    private ContractTypeRepository repository;

    @Autowired
    private ContractTypeMapper mapper;

    @Override
    public Mono<PaginationResponse<ContractTypeDTO>> listContractTypes(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findAllBy(pageable),
                () -> repository.count()
        );
    }

    @Override
    public Mono<ContractTypeDTO> createContractType(ContractTypeDTO contractTypeDto) {
        ContractType entity = mapper.toEntity(contractTypeDto);
        entity.setDateCreated(LocalDateTime.now());
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ContractTypeDTO> getContractType(Long contractId) {
        return repository.findById(contractId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ContractTypeDTO> updateContractType(Long contractId, ContractTypeDTO contractTypeDto) {
        return repository.findById(contractId)
                .flatMap(contractType -> {
                    ContractType updatedContractType = mapper.toEntity(contractTypeDto);
                    updatedContractType.setContractId(contractId); // Preserve actual id
                    updatedContractType.setDateUpdated(LocalDateTime.now()); // Update the field
                    return repository.save(updatedContractType);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteContractType(Long contractId) {
        return repository.findById(contractId)
                .flatMap(repository::delete);
    }
}