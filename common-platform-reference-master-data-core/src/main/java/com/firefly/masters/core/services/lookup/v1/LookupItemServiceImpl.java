package com.firefly.masters.core.services.lookup.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.masters.core.mappers.lookup.v1.LookupItemMapper;
import com.firefly.masters.interfaces.dtos.lookup.v1.LookupItemDTO;
import com.firefly.masters.models.entities.lookup.v1.LookupItem;
import com.firefly.masters.models.repositories.lookup.v1.LookupItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Service
@Transactional
public class LookupItemServiceImpl implements LookupItemService {

    @Autowired
    private LookupItemRepository repository;

    @Autowired
    private LookupItemMapper mapper;

    @Override
    public Mono<PaginationResponse<LookupItemDTO>> listItems(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findAllBy(pageable),
                () -> repository.count()
        );
    }

    @Override
    public Flux<LookupItemDTO> getItemsByDomain(UUID domainId) {
        return repository.findByDomainId(domainId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<LookupItemDTO> createItem(LookupItemDTO itemDto) {
        LookupItem item = mapper.toEntity(itemDto);
        return repository.save(item)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<LookupItemDTO> getItem(UUID itemId) {
        return repository.findById(itemId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<LookupItemDTO> updateItem(UUID itemId, LookupItemDTO itemDto) {
        return repository.findById(itemId)
                .flatMap(foundItem -> {
                    LookupItem updatedItem = mapper.toEntity(itemDto);
                    updatedItem.setItemId(foundItem.getItemId());
                    return repository.save(updatedItem);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteItem(UUID itemId) {
        return repository.findById(itemId)
                .flatMap(repository::delete);
    }
}