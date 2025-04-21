package com.catalis.masters.core.services.legal.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.masters.core.mappers.legal.v1.LegalFormMapper;
import com.catalis.masters.interfaces.dtos.legal.v1.LegalFormDTO;
import com.catalis.masters.models.entities.legal.v1.LegalForm;
import com.catalis.masters.models.repositories.legal.v1.LegalFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class LegalFormServiceImpl implements LegalFormService {

    @Autowired
    private LegalFormRepository repository;

    @Autowired
    private LegalFormMapper mapper;

    @Override
    public Mono<PaginationResponse<LegalFormDTO>> listLegalForms(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findAllBy(pageable),
                () -> repository.count()
        );
    }

    @Override
    public Flux<LegalFormDTO> getLegalFormsByCountry(Long countryId) {
        return repository.findByCountryId(countryId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<LegalFormDTO> createLegalForm(LegalFormDTO legalFormDto) {
        LegalForm legalForm = mapper.toEntity(legalFormDto);
        return repository.save(legalForm)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<LegalFormDTO> getLegalForm(Long legalFormId) {
        return repository.findById(legalFormId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<LegalFormDTO> updateLegalForm(Long legalFormId, LegalFormDTO legalFormDto) {
        return repository.findById(legalFormId)
                .flatMap(foundLegalForm -> {
                    LegalForm updatedLegalForm = mapper.toEntity(legalFormDto);
                    updatedLegalForm.setLegalFormId(foundLegalForm.getLegalFormId());
                    return repository.save(updatedLegalForm);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteLegalForm(Long legalFormId) {
        return repository.findById(legalFormId)
                .flatMap(repository::delete);
    }
}