package com.firefly.masters.core.services.country.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.masters.core.utils.TestPaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.masters.core.mappers.country.v1.CountryMapper;
import com.firefly.masters.interfaces.dtos.country.v1.CountryDTO;
import com.firefly.masters.interfaces.enums.commons.v1.StatusEnum;
import com.firefly.masters.models.entities.country.v1.Country;
import com.firefly.masters.models.repositories.country.v1.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class CountryServiceImplTest {

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private CountryMapper countryMapper;

    @InjectMocks
    private CountryServiceImpl countryService;

    private Country country;
    private CountryDTO countryDTO;
    private TestPaginationRequest paginationRequest;

    @Mock
    private FilterRequest<CountryDTO> filterRequest;

    @BeforeEach
    void setUp() {
        // Setup test data
        country = new Country();
        country.setCountryId(1L);
        country.setIsoCode("US");
        country.setCountryName("United States");
        country.setStatus(StatusEnum.ACTIVE);
        country.setDateCreated(LocalDateTime.now());
        country.setDateUpdated(LocalDateTime.now());

        countryDTO = new CountryDTO();
        countryDTO.setIsoCode("US");
        countryDTO.setCountryName("United States");
        countryDTO.setStatus(StatusEnum.ACTIVE);
        countryDTO.setDateCreated(LocalDateTime.now());
        countryDTO.setDateUpdated(LocalDateTime.now());

        paginationRequest = new TestPaginationRequest(0, 10);
    }

    @Test
    void createCountry_ShouldReturnCreatedCountry() {
        // Arrange
        when(countryMapper.toEntity(any(CountryDTO.class))).thenReturn(country);
        when(countryRepository.save(any(Country.class))).thenReturn(Mono.just(country));
        when(countryMapper.toDTO(any(Country.class))).thenReturn(countryDTO);

        // Act
        Mono<CountryDTO> result = countryService.createCountry(countryDTO);

        // Assert
        StepVerifier.create(result)
                .expectNext(countryDTO)
                .verifyComplete();

        verify(countryMapper).toEntity(any(CountryDTO.class));
        verify(countryRepository).save(any(Country.class));
        verify(countryMapper).toDTO(any(Country.class));
    }

    @Test
    void getCountry_ShouldReturnCountryWhenFound() {
        // Arrange
        when(countryRepository.findById(anyLong())).thenReturn(Mono.just(country));
        when(countryMapper.toDTO(any(Country.class))).thenReturn(countryDTO);

        // Act
        Mono<CountryDTO> result = countryService.getCountry(1L);

        // Assert
        StepVerifier.create(result)
                .expectNext(countryDTO)
                .verifyComplete();

        verify(countryRepository).findById(anyLong());
        verify(countryMapper).toDTO(any(Country.class));
    }

    @Test
    void getCountry_ShouldReturnEmptyWhenNotFound() {
        // Arrange
        when(countryRepository.findById(anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<CountryDTO> result = countryService.getCountry(1L);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(countryRepository).findById(anyLong());
        verify(countryMapper, never()).toDTO(any(Country.class));
    }

    @Test
    void updateCountry_ShouldReturnUpdatedCountryWhenFound() {
        // Arrange
        when(countryRepository.findById(anyLong())).thenReturn(Mono.just(country));
        when(countryMapper.toEntity(any(CountryDTO.class))).thenReturn(country);
        when(countryRepository.save(any(Country.class))).thenReturn(Mono.just(country));
        when(countryMapper.toDTO(any(Country.class))).thenReturn(countryDTO);

        // Act
        Mono<CountryDTO> result = countryService.updateCountry(1L, countryDTO);

        // Assert
        StepVerifier.create(result)
                .expectNext(countryDTO)
                .verifyComplete();

        verify(countryRepository).findById(anyLong());
        verify(countryMapper).toEntity(any(CountryDTO.class));
        verify(countryRepository).save(any(Country.class));
        verify(countryMapper).toDTO(any(Country.class));
    }

    @Test
    void updateCountry_ShouldReturnEmptyWhenNotFound() {
        // Arrange
        when(countryRepository.findById(anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<CountryDTO> result = countryService.updateCountry(1L, countryDTO);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(countryRepository).findById(anyLong());
        verify(countryMapper, never()).toEntity(any(CountryDTO.class));
        verify(countryRepository, never()).save(any(Country.class));
        verify(countryMapper, never()).toDTO(any(Country.class));
    }

    @Test
    void deleteCountry_ShouldDeleteWhenFound() {
        // Arrange
        when(countryRepository.findById(anyLong())).thenReturn(Mono.just(country));
        when(countryRepository.delete(any(Country.class))).thenReturn(Mono.empty());

        // Act
        Mono<Void> result = countryService.deleteCountry(1L);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(countryRepository).findById(anyLong());
        verify(countryRepository).delete(any(Country.class));
    }

    @Test
    void deleteCountry_ShouldReturnEmptyWhenNotFound() {
        // Arrange
        when(countryRepository.findById(anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<Void> result = countryService.deleteCountry(1L);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(countryRepository).findById(anyLong());
        verify(countryRepository, never()).delete(any(Country.class));
    }
}
