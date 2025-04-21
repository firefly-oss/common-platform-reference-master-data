package com.catalis.masters.core.services.branch.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.masters.core.mappers.branch.v1.BranchMapper;
import com.catalis.masters.interfaces.dtos.branch.v1.BranchDTO;
import com.catalis.masters.models.repositories.branch.v1.BranchRepository;
import com.catalis.masters.models.entities.branch.v1.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository repository;

    @Autowired
    private BranchMapper mapper;

    @Override
    public Mono<PaginationResponse<BranchDTO>> listBranches(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findAllBy(pageable),
                () -> repository.count()
        );
    }

    @Override
    public Mono<BranchDTO> createBranch(BranchDTO branchDto) {
        return Mono.just(branchDto)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<BranchDTO> getBranch(Long branchId) {
        return repository.findById(branchId)
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.error(new RuntimeException("Branch not found")));
    }

    @Override
    public Mono<BranchDTO> updateBranch(Long branchId, BranchDTO branchDto) {
        return repository.findById(branchId)
                .switchIfEmpty(Mono.error(new RuntimeException("Branch not found")))
                .flatMap(existingBranch -> {
                    Branch updatedEntity = mapper.toEntity(branchDto);
                    updatedEntity.setBranchId(existingBranch.getBranchId());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteBranch(Long branchId) {
        return repository.findById(branchId)
                .switchIfEmpty(Mono.error(new RuntimeException("Branch not found")))
                .flatMap(repository::delete);
    }
}
