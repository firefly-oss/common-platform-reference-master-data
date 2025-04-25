package com.catalis.masters.core.services.locale.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.masters.core.mappers.locale.v1.LanguageLocaleMapper;
import com.catalis.masters.interfaces.dtos.locale.v1.LanguageLocaleDTO;
import com.catalis.masters.models.entities.locale.v1.LanguageLocale;
import com.catalis.masters.models.repositories.locale.v1.LanguageLocaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class LanguageLocaleServiceImpl implements LanguageLocaleService {

    @Autowired
    private LanguageLocaleRepository repository;

    @Autowired
    private LanguageLocaleMapper mapper;

    @Override
    public Mono<PaginationResponse<LanguageLocaleDTO>> listLanguageLocales(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findAllBy(pageable),
                () -> repository.count()
        );
    }

    @Override
    public Mono<LanguageLocaleDTO> createLanguageLocale(LanguageLocaleDTO dto) {
        LanguageLocale entity = mapper.toEntity(dto);
        entity.setLocaleId(null); // Ensure the ID is null for a new entity
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<LanguageLocaleDTO> getLanguageLocale(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<LanguageLocaleDTO> updateLanguageLocale(Long id, LanguageLocaleDTO dto) {
        return repository.findById(id)
                .flatMap(existingEntity -> {
                    LanguageLocale updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setLocaleId(id); // Ensure the ID is preserved
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteLanguageLocale(Long id) {
        return repository.findById(id)
                .flatMap(repository::delete);
    }
}
