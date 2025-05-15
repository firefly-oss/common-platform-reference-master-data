package com.catalis.masters.core.services.transaction.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.masters.core.mappers.transaction.v1.TransactionCategoryLocalizationMapper;
import com.catalis.masters.interfaces.dtos.transaction.v1.TransactionCategoryLocalizationDTO;
import com.catalis.masters.models.entities.transaction.v1.TransactionCategoryLocalization;
import com.catalis.masters.models.repositories.transaction.v1.TransactionCategoryLocalizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Implementation of the TransactionCategoryLocalizationService interface.
 */
@Service
@Transactional
public class TransactionCategoryLocalizationServiceImpl implements TransactionCategoryLocalizationService {

    @Autowired
    private TransactionCategoryLocalizationRepository repository;

    @Autowired
    private TransactionCategoryLocalizationMapper mapper;

    @Override
    public Flux<TransactionCategoryLocalizationDTO> getLocalizationsByCategoryId(Long categoryId) {
        return repository.findByCategoryId(categoryId)
                .map(mapper::toDTO)
                .switchIfEmpty(Flux.error(new RuntimeException("No localizations found for category ID: " + categoryId)));
    }

    @Override
    public Mono<PaginationResponse<TransactionCategoryLocalizationDTO>> listLocalizationsByCategoryId(Long categoryId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findByCategoryId(categoryId, pageable),
                () -> repository.countByCategoryId(categoryId)
        );
    }

    @Override
    public Mono<TransactionCategoryLocalizationDTO> createTransactionCategoryLocalization(TransactionCategoryLocalizationDTO localizationDTO) {
        // Set audit fields
        LocalDateTime now = LocalDateTime.now();
        localizationDTO.setDateCreated(now);
        localizationDTO.setDateUpdated(now);

        return Mono.just(localizationDTO)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO)
                .onErrorResume(e -> Mono.error(new RuntimeException("Error creating transaction category localization: " + e.getMessage(), e)));
    }

    @Override
    public Mono<TransactionCategoryLocalizationDTO> getTransactionCategoryLocalization(Long localizationId) {
        return repository.findById(localizationId)
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.error(new RuntimeException("Transaction category localization not found with ID: " + localizationId)));
    }

    @Override
    public Mono<TransactionCategoryLocalizationDTO> getTransactionCategoryLocalizationByCategoryAndLocale(Long categoryId, Long localeId) {
        return repository.findByCategoryIdAndLocaleId(categoryId, localeId)
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.error(new RuntimeException("Transaction category localization not found with category ID: " + categoryId + " and locale ID: " + localeId)));
    }

    @Override
    public Mono<TransactionCategoryLocalizationDTO> updateTransactionCategoryLocalization(Long localizationId, TransactionCategoryLocalizationDTO localizationDTO) {
        return repository.findById(localizationId)
                .flatMap(existingLocalization -> {
                    TransactionCategoryLocalization updatedLocalization = mapper.toEntity(localizationDTO);
                    updatedLocalization.setLocalizationId(localizationId);
                    updatedLocalization.setDateCreated(existingLocalization.getDateCreated());
                    updatedLocalization.setDateUpdated(LocalDateTime.now());
                    return repository.save(updatedLocalization);
                })
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.error(new RuntimeException("Transaction category localization not found with ID: " + localizationId)));
    }

    @Override
    public Mono<Void> deleteTransactionCategoryLocalization(Long localizationId) {
        return repository.findById(localizationId)
                .switchIfEmpty(Mono.error(new RuntimeException("Transaction category localization not found with ID: " + localizationId)))
                .flatMap(repository::delete);
    }
}
