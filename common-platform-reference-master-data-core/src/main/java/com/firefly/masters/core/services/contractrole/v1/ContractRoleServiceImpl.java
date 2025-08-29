package com.firefly.masters.core.services.contractrole.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.masters.core.mappers.contractrole.v1.ContractRoleMapper;
import com.firefly.masters.interfaces.dtos.contractrole.v1.ContractRoleDTO;
import com.firefly.masters.models.entities.contractrole.v1.ContractRole;
import com.firefly.masters.models.repositories.contractrole.v1.ContractRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@Transactional
public class ContractRoleServiceImpl implements ContractRoleService {

    @Autowired
    private ContractRoleRepository repository;

    @Autowired
    private ContractRoleMapper mapper;

    @Override
    public Mono<PaginationResponse<ContractRoleDTO>> listContractRoles(FilterRequest<ContractRoleDTO> filterRequest) {
        return FilterUtils
                .createFilter(
                        ContractRole.class,
                        mapper::toDTO
                )
                .filter(filterRequest);
    }

    @Override
    public Mono<ContractRoleDTO> createContractRole(ContractRoleDTO contractRoleDto) {
        ContractRole entity = mapper.toEntity(contractRoleDto);
        entity.setDateCreated(LocalDateTime.now());
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ContractRoleDTO> getContractRole(Long roleId) {
        return repository.findById(roleId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ContractRoleDTO> updateContractRole(Long roleId, ContractRoleDTO contractRoleDto) {
        return repository.findById(roleId)
                .flatMap(role -> {
                    ContractRole updated = mapper.toEntity(contractRoleDto);
                    updated.setRoleId(roleId);
                    updated.setDateUpdated(LocalDateTime.now());
                    return repository.save(updated);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteContractRole(Long roleId) {
        return repository.findById(roleId)
                .flatMap(repository::delete);
    }
}
