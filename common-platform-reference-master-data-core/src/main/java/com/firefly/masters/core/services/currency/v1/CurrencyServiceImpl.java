package com.firefly.masters.core.services.currency.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.masters.core.mappers.currency.v1.CurrencyMapper;
import com.firefly.masters.interfaces.dtos.currency.v1.CurrencyDTO;
import com.firefly.masters.models.entities.currency.v1.Currency;
import com.firefly.masters.models.repositories.currency.v1.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyRepository repository;

    @Autowired
    private CurrencyMapper mapper;

    @Override
    public Mono<PaginationResponse<CurrencyDTO>> listCurrencies(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findAllBy(pageable),
                () -> repository.count()
        );
    }

    @Override
    public Mono<CurrencyDTO> createCurrency(CurrencyDTO currencyDto) {
        Currency currency = mapper.toEntity(currencyDto);
        return repository.save(currency)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CurrencyDTO> getCurrency(Long currencyId) {
        return repository.findById(currencyId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CurrencyDTO> updateCurrency(Long currencyId, CurrencyDTO currencyDto) {
        return repository.findById(currencyId)
                .flatMap(existingCurrency -> {
                    Currency updatedCurrency = mapper.toEntity(currencyDto);
                    updatedCurrency.setCurrencyId(currencyId); // Ensure ID matches the existing record
                    return repository.save(updatedCurrency);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteCurrency(Long currencyId) {
        return repository.deleteById(currencyId);
    }
}
