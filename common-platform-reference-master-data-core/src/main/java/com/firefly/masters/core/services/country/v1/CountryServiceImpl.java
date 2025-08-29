package com.firefly.masters.core.services.country.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.masters.core.mappers.country.v1.CountryMapper;
import com.firefly.masters.interfaces.dtos.country.v1.CountryDTO;
import com.firefly.masters.models.entities.country.v1.Country;
import com.firefly.masters.models.repositories.country.v1.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository repository;

    @Autowired
    private CountryMapper mapper;

    @Override
    public Mono<PaginationResponse<CountryDTO>> listCountries(FilterRequest<CountryDTO> filterRequest) {
        return FilterUtils
                .createFilter(
                        Country.class,
                        mapper::toDTO
                )
                .filter(filterRequest);
    }

    @Override
    public Mono<CountryDTO> createCountry(CountryDTO countryDto) {
        Country country = mapper.toEntity(countryDto);
        return repository.save(country)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CountryDTO> getCountry(Long countryId) {
        return repository.findById(countryId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CountryDTO> updateCountry(Long countryId, CountryDTO countryDto) {
        return repository.findById(countryId)
                .flatMap(foundCountry -> {
                    Country updatedCountry = mapper.toEntity(countryDto);
                    updatedCountry.setCountryId(foundCountry.getCountryId());
                    return repository.save(updatedCountry);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteCountry(Long countryId) {
        return repository.findById(countryId)
                .flatMap(repository::delete);
    }
}
