package com.catalis.masters.core.services.assettype.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.masters.core.mappers.assettype.v1.AssetTypeMapper;
import com.catalis.masters.interfaces.dtos.assettype.v1.AssetTypeDTO;
import com.catalis.masters.models.entities.assettype.v1.AssetType;
import com.catalis.masters.models.repositories.assettype.v1.AssetTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@Transactional
public class AssetTypeServiceImpl implements AssetTypeService {

    @Autowired
    private AssetTypeRepository repository;

    @Autowired
    private AssetTypeMapper mapper;

    @Override
    public Mono<PaginationResponse<AssetTypeDTO>> listAssetTypes(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findAllBy(pageable),
                () -> repository.count()
        );
    }

    @Override
    public Mono<AssetTypeDTO> createAssetType(AssetTypeDTO assetTypeDto) {
        AssetType entity = mapper.toEntity(assetTypeDto);
        entity.setDateCreated(LocalDateTime.now());
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<AssetTypeDTO> getAssetType(Long assetId) {
        return repository.findById(assetId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<AssetTypeDTO> updateAssetType(Long assetId, AssetTypeDTO assetTypeDto) {
        return repository.findById(assetId)
                .flatMap(assetType -> {
                    AssetType updatedAssetType = mapper.toEntity(assetTypeDto);
                    updatedAssetType.setAssetId(assetId); // Preserve actual id
                    updatedAssetType.setDateUpdated(LocalDateTime.now()); // Update the field
                    return repository.save(updatedAssetType);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteAssetType(Long assetId) {
        return repository.findById(assetId)
                .flatMap(repository::delete);
    }
}