package com.catalis.masters.core.services.lookup.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.masters.core.mappers.lookup.v1.LookupDomainMapper;
import com.catalis.masters.interfaces.dtos.lookup.v1.LookupDomainDTO;
import com.catalis.masters.models.entities.lookup.v1.LookupDomain;
import com.catalis.masters.models.repositories.lookup.v1.LookupDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class LookupDomainServiceImpl implements LookupDomainService {

    @Autowired
    private LookupDomainRepository repository;

    @Autowired
    private LookupDomainMapper mapper;

    @Override
    public Mono<PaginationResponse<LookupDomainDTO>> listDomains(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findAllBy(pageable),
                () -> repository.count()
        );
    }

    @Override
    public Mono<LookupDomainDTO> createDomain(LookupDomainDTO domainDto) {
        LookupDomain domain = mapper.toEntity(domainDto);
        return repository.save(domain)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<LookupDomainDTO> getDomain(Long domainId) {
        return repository.findById(domainId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<LookupDomainDTO> updateDomain(Long domainId, LookupDomainDTO domainDto) {
        return repository.findById(domainId)
                .flatMap(foundDomain -> {
                    LookupDomain updatedDomain = mapper.toEntity(domainDto);
                    updatedDomain.setDomainId(foundDomain.getDomainId());
                    return repository.save(updatedDomain);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteDomain(Long domainId) {
        return repository.findById(domainId)
                .flatMap(repository::delete);
    }
}