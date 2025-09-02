package com.firefly.masters.core.services.currency.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.masters.core.utils.TestPaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.masters.core.mappers.currency.v1.CurrencyMapper;
import com.firefly.masters.interfaces.dtos.currency.v1.CurrencyDTO;
import com.firefly.masters.interfaces.enums.commons.v1.StatusEnum;
import com.firefly.masters.models.entities.currency.v1.Currency;
import com.firefly.masters.models.repositories.currency.v1.CurrencyRepository;
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
public class CurrencyServiceImplTest {

    @Mock
    private CurrencyRepository currencyRepository;

    @Mock
    private CurrencyMapper currencyMapper;

    @InjectMocks
    private CurrencyServiceImpl currencyService;

    private Currency currency;
    private CurrencyDTO currencyDTO;
    private TestPaginationRequest paginationRequest;

    @BeforeEach
    void setUp() {
        // Setup test data
        currency = new Currency();
        currency.setCurrencyId(1L);
        currency.setIsoCode("USD");
        currency.setCurrencyName("US Dollar");
        currency.setSymbol("$");
        currency.setDecimalPrecision(2);
        currency.setIsMajor(true);
        currency.setStatus(StatusEnum.ACTIVE);
        currency.setDateCreated(LocalDateTime.now());
        currency.setDateUpdated(LocalDateTime.now());

        currencyDTO = new CurrencyDTO();
        currencyDTO.setCurrencyId(1L);
        currencyDTO.setIsoCode("USD");
        currencyDTO.setCurrencyName("US Dollar");
        currencyDTO.setSymbol("$");
        currencyDTO.setDecimalPrecision(2);
        currencyDTO.setIsMajor(true);
        currencyDTO.setStatus(StatusEnum.ACTIVE);
        currencyDTO.setDateCreated(LocalDateTime.now());
        currencyDTO.setDateUpdated(LocalDateTime.now());

        paginationRequest = new TestPaginationRequest(0, 10);
    }

    @Test
    void listCurrencies_ShouldReturnPaginatedResponse() {
        // Arrange
        Pageable pageable = paginationRequest.toPageable();
        when(currencyRepository.findAllBy(any(Pageable.class))).thenReturn(Flux.just(currency));
        when(currencyRepository.count()).thenReturn(Mono.just(1L));
        when(currencyMapper.toDTO(any(Currency.class))).thenReturn(currencyDTO);

        // Act
        Mono<PaginationResponse<CurrencyDTO>> result = currencyService.listCurrencies(paginationRequest);

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(response -> {
                    return response.getContent().size() == 1 &&
                            response.getContent().get(0).getCurrencyId().equals(1L) &&
                            response.getTotalElements() == 1L;
                })
                .verifyComplete();

        verify(currencyRepository).findAllBy(any(Pageable.class));
        verify(currencyRepository).count();
        verify(currencyMapper).toDTO(any(Currency.class));
    }

    @Test
    void createCurrency_ShouldReturnCreatedCurrency() {
        // Arrange
        when(currencyMapper.toEntity(any(CurrencyDTO.class))).thenReturn(currency);
        when(currencyRepository.save(any(Currency.class))).thenReturn(Mono.just(currency));
        when(currencyMapper.toDTO(any(Currency.class))).thenReturn(currencyDTO);

        // Act
        Mono<CurrencyDTO> result = currencyService.createCurrency(currencyDTO);

        // Assert
        StepVerifier.create(result)
                .expectNext(currencyDTO)
                .verifyComplete();

        verify(currencyMapper).toEntity(any(CurrencyDTO.class));
        verify(currencyRepository).save(any(Currency.class));
        verify(currencyMapper).toDTO(any(Currency.class));
    }

    @Test
    void getCurrency_ShouldReturnCurrencyWhenFound() {
        // Arrange
        when(currencyRepository.findById(anyLong())).thenReturn(Mono.just(currency));
        when(currencyMapper.toDTO(any(Currency.class))).thenReturn(currencyDTO);

        // Act
        Mono<CurrencyDTO> result = currencyService.getCurrency(1L);

        // Assert
        StepVerifier.create(result)
                .expectNext(currencyDTO)
                .verifyComplete();

        verify(currencyRepository).findById(anyLong());
        verify(currencyMapper).toDTO(any(Currency.class));
    }

    @Test
    void getCurrency_ShouldReturnEmptyWhenNotFound() {
        // Arrange
        when(currencyRepository.findById(anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<CurrencyDTO> result = currencyService.getCurrency(1L);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(currencyRepository).findById(anyLong());
        verify(currencyMapper, never()).toDTO(any(Currency.class));
    }

    @Test
    void updateCurrency_ShouldReturnUpdatedCurrencyWhenFound() {
        // Arrange
        when(currencyRepository.findById(anyLong())).thenReturn(Mono.just(currency));
        when(currencyMapper.toEntity(any(CurrencyDTO.class))).thenReturn(currency);
        when(currencyRepository.save(any(Currency.class))).thenReturn(Mono.just(currency));
        when(currencyMapper.toDTO(any(Currency.class))).thenReturn(currencyDTO);

        // Act
        Mono<CurrencyDTO> result = currencyService.updateCurrency(1L, currencyDTO);

        // Assert
        StepVerifier.create(result)
                .expectNext(currencyDTO)
                .verifyComplete();

        verify(currencyRepository).findById(anyLong());
        verify(currencyMapper).toEntity(any(CurrencyDTO.class));
        verify(currencyRepository).save(any(Currency.class));
        verify(currencyMapper).toDTO(any(Currency.class));
    }

    @Test
    void updateCurrency_ShouldReturnEmptyWhenNotFound() {
        // Arrange
        when(currencyRepository.findById(anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<CurrencyDTO> result = currencyService.updateCurrency(1L, currencyDTO);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(currencyRepository).findById(anyLong());
        verify(currencyMapper, never()).toEntity(any(CurrencyDTO.class));
        verify(currencyRepository, never()).save(any(Currency.class));
        verify(currencyMapper, never()).toDTO(any(Currency.class));
    }

    @Test
    void deleteCurrency_ShouldDeleteWhenFound() {
        // Arrange
        when(currencyRepository.deleteById(anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<Void> result = currencyService.deleteCurrency(1L);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(currencyRepository).deleteById(anyLong());
    }

    @Test
    void deleteCurrency_ShouldReturnEmptyWhenNotFound() {
        // Arrange
        when(currencyRepository.deleteById(anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<Void> result = currencyService.deleteCurrency(1L);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(currencyRepository).deleteById(anyLong());
    }
}
