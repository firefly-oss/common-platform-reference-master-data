package com.catalis.masters.core.services.bank.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.masters.core.mappers.bank.v1.BankInstitutionCodeMapper;
import com.catalis.masters.interfaces.dtos.bank.v1.BankInstitutionCodeDTO;
import com.catalis.masters.models.entities.bank.v1.BankInstitutionCode;
import com.catalis.masters.models.repositories.bank.v1.BankInstitutionCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class BankInstitutionCodeServiceImpl implements BankInstitutionCodeService {

    @Autowired
    private BankInstitutionCodeRepository repository;

    @Autowired
    private BankInstitutionCodeMapper mapper;

    @Override
    public Mono<PaginationResponse<BankInstitutionCodeDTO>> listBankInstitutionCodes(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findAllBy(pageable),
                () -> repository.count()
        );
    }

    @Override
    public Mono<BankInstitutionCodeDTO> createBankInstitutionCode(BankInstitutionCodeDTO dto) {
        BankInstitutionCode entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<BankInstitutionCodeDTO> getBankInstitutionCode(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<BankInstitutionCodeDTO> updateBankInstitutionCode(Long id, BankInstitutionCodeDTO dto) {
        return repository.findById(id)
                .flatMap(existingEntity -> {
                    BankInstitutionCode updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setInstitutionId(existingEntity.getInstitutionId());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteBankInstitutionCode(Long id) {
        return repository.deleteById(id);
    }
}
