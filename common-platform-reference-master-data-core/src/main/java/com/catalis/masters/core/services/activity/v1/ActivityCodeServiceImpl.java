package com.catalis.masters.core.services.activity.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.masters.core.mappers.activity.v1.ActivityCodeMapper;
import com.catalis.masters.interfaces.dtos.activity.v1.ActivityCodeDTO;
import com.catalis.masters.models.entities.activity.v1.ActivityCode;
import com.catalis.masters.models.repositories.activity.v1.ActivityCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ActivityCodeServiceImpl implements ActivityCodeService {

    @Autowired
    private ActivityCodeRepository repository;

    @Autowired
    private ActivityCodeMapper mapper;

    @Override
    public Mono<PaginationResponse<ActivityCodeDTO>> listActivityCodes(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findAllBy(pageable),
                () -> repository.count()
        );
    }

    @Override
    public Flux<ActivityCodeDTO> getActivityCodesByCountry(Long countryId) {
        return repository.findByCountryId(countryId)
                .map(mapper::toDTO);
    }

    @Override
    public Flux<ActivityCodeDTO> getChildActivityCodes(Long parentCodeId) {
        return repository.findByParentCodeId(parentCodeId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ActivityCodeDTO> createActivityCode(ActivityCodeDTO activityCodeDto) {
        ActivityCode activityCode = mapper.toEntity(activityCodeDto);
        return repository.save(activityCode)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ActivityCodeDTO> getActivityCode(Long activityCodeId) {
        return repository.findById(activityCodeId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ActivityCodeDTO> updateActivityCode(Long activityCodeId, ActivityCodeDTO activityCodeDto) {
        return repository.findById(activityCodeId)
                .flatMap(foundActivityCode -> {
                    ActivityCode updatedActivityCode = mapper.toEntity(activityCodeDto);
                    updatedActivityCode.setActivityCodeId(foundActivityCode.getActivityCodeId());
                    return repository.save(updatedActivityCode);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteActivityCode(Long activityCodeId) {
        return repository.findById(activityCodeId)
                .flatMap(repository::delete);
    }
}