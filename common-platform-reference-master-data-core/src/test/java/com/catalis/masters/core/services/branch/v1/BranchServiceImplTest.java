package com.catalis.masters.core.services.branch.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.masters.core.utils.TestPaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.masters.core.mappers.branch.v1.BranchMapper;
import com.catalis.masters.interfaces.dtos.branch.v1.BranchDTO;
import com.catalis.masters.interfaces.enums.commons.v1.StatusEnum;
import com.catalis.masters.models.entities.branch.v1.Branch;
import com.catalis.masters.models.repositories.branch.v1.BranchRepository;
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

@ExtendWith(MockitoExtension.class)
public class BranchServiceImplTest {

    @Mock
    private BranchRepository branchRepository;

    @Mock
    private BranchMapper branchMapper;

    @InjectMocks
    private BranchServiceImpl branchService;

    private Branch branch;
    private BranchDTO branchDTO;
    private TestPaginationRequest paginationRequest;

    @BeforeEach
    void setUp() {
        // Setup test data
        branch = new Branch();
        branch.setBranchId(1L);
        branch.setBranchCode("NYC-001");
        branch.setBranchName("Manhattan Main Branch");
        branch.setAddress("123 Wall Street");
        branch.setPostalCode("10005");
        branch.setCity("New York");
        branch.setDivisionId(2L);
        branch.setCountryId(1L);
        branch.setPhoneNumber("+1-212-555-1234");
        branch.setEmail("manhattan@bank.com");
        branch.setBranchTypeLkpId(1L);
        branch.setBranchManagerId(101L);
        branch.setOpeningHours("Mon-Fri: 9:00-17:00");
        branch.setStatus(StatusEnum.ACTIVE);
        branch.setDateCreated(LocalDateTime.now());
        branch.setDateUpdated(LocalDateTime.now());

        branchDTO = new BranchDTO();
        branchDTO.setBranchId(1L);
        branchDTO.setBranchCode("NYC-001");
        branchDTO.setBranchName("Manhattan Main Branch");
        branchDTO.setAddress("123 Wall Street");
        branchDTO.setPostalCode("10005");
        branchDTO.setCity("New York");
        branchDTO.setDivisionId(2L);
        branchDTO.setCountryId(1L);
        branchDTO.setPhoneNumber("+1-212-555-1234");
        branchDTO.setEmail("manhattan@bank.com");
        branchDTO.setBranchTypeLkpId(1L);
        branchDTO.setBranchManagerId(101L);
        branchDTO.setOpeningHours("Mon-Fri: 9:00-17:00");
        branchDTO.setStatus(StatusEnum.ACTIVE);
        branchDTO.setDateCreated(LocalDateTime.now());
        branchDTO.setDateUpdated(LocalDateTime.now());

        paginationRequest = new TestPaginationRequest(0, 10);
    }

    @Test
    void listBranches_ShouldReturnPaginatedResponse() {
        // Arrange
        Pageable pageable = paginationRequest.toPageable();
        when(branchRepository.findAllBy(any(Pageable.class))).thenReturn(Flux.just(branch));
        when(branchRepository.count()).thenReturn(Mono.just(1L));
        when(branchMapper.toDTO(any(Branch.class))).thenReturn(branchDTO);

        // Act
        Mono<PaginationResponse<BranchDTO>> result = branchService.listBranches(paginationRequest);

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(response -> {
                    return response.getContent().size() == 1 &&
                            response.getContent().get(0).getBranchId().equals(1L) &&
                            response.getTotalElements() == 1L;
                })
                .verifyComplete();

        verify(branchRepository).findAllBy(any(Pageable.class));
        verify(branchRepository).count();
        verify(branchMapper).toDTO(any(Branch.class));
    }

    @Test
    void createBranch_ShouldReturnCreatedBranch() {
        // Arrange
        when(branchMapper.toEntity(any(BranchDTO.class))).thenReturn(branch);
        when(branchRepository.save(any(Branch.class))).thenReturn(Mono.just(branch));
        when(branchMapper.toDTO(any(Branch.class))).thenReturn(branchDTO);

        // Act
        Mono<BranchDTO> result = branchService.createBranch(branchDTO);

        // Assert
        StepVerifier.create(result)
                .expectNext(branchDTO)
                .verifyComplete();

        verify(branchMapper).toEntity(any(BranchDTO.class));
        verify(branchRepository).save(any(Branch.class));
        verify(branchMapper).toDTO(any(Branch.class));
    }

    @Test
    void getBranch_ShouldReturnBranchWhenFound() {
        // Arrange
        when(branchRepository.findById(anyLong())).thenReturn(Mono.just(branch));
        when(branchMapper.toDTO(any(Branch.class))).thenReturn(branchDTO);

        // Act
        Mono<BranchDTO> result = branchService.getBranch(1L);

        // Assert
        StepVerifier.create(result)
                .expectNext(branchDTO)
                .verifyComplete();

        verify(branchRepository).findById(anyLong());
        verify(branchMapper).toDTO(any(Branch.class));
    }

    @Test
    void getBranch_ShouldReturnEmptyWhenNotFound() {
        // Arrange
        when(branchRepository.findById(anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<BranchDTO> result = branchService.getBranch(1L);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(branchRepository).findById(anyLong());
        verify(branchMapper, never()).toDTO(any(Branch.class));
    }

    @Test
    void updateBranch_ShouldReturnUpdatedBranchWhenFound() {
        // Arrange
        when(branchRepository.findById(anyLong())).thenReturn(Mono.just(branch));
        when(branchMapper.toEntity(any(BranchDTO.class))).thenReturn(branch);
        when(branchRepository.save(any(Branch.class))).thenReturn(Mono.just(branch));
        when(branchMapper.toDTO(any(Branch.class))).thenReturn(branchDTO);

        // Act
        Mono<BranchDTO> result = branchService.updateBranch(1L, branchDTO);

        // Assert
        StepVerifier.create(result)
                .expectNext(branchDTO)
                .verifyComplete();

        verify(branchRepository).findById(anyLong());
        verify(branchMapper).toEntity(any(BranchDTO.class));
        verify(branchRepository).save(any(Branch.class));
        verify(branchMapper).toDTO(any(Branch.class));
    }

    @Test
    void updateBranch_ShouldReturnEmptyWhenNotFound() {
        // Arrange
        when(branchRepository.findById(anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<BranchDTO> result = branchService.updateBranch(1L, branchDTO);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(branchRepository).findById(anyLong());
        verify(branchMapper, never()).toEntity(any(BranchDTO.class));
        verify(branchRepository, never()).save(any(Branch.class));
        verify(branchMapper, never()).toDTO(any(Branch.class));
    }

    @Test
    void deleteBranch_ShouldDeleteWhenFound() {
        // Arrange
        when(branchRepository.findById(anyLong())).thenReturn(Mono.just(branch));
        when(branchRepository.delete(any(Branch.class))).thenReturn(Mono.empty());

        // Act
        Mono<Void> result = branchService.deleteBranch(1L);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(branchRepository).findById(anyLong());
        verify(branchRepository).delete(any(Branch.class));
    }

    @Test
    void deleteBranch_ShouldReturnEmptyWhenNotFound() {
        // Arrange
        when(branchRepository.findById(anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<Void> result = branchService.deleteBranch(1L);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(branchRepository).findById(anyLong());
        verify(branchRepository, never()).delete(any(Branch.class));
    }
}
