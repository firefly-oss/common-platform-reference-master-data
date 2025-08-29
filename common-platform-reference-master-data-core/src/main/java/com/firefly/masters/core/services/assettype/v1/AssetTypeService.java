package com.firefly.masters.core.services.assettype.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.masters.interfaces.dtos.assettype.v1.AssetTypeDTO;
import reactor.core.publisher.Mono;

/**
 * Service interface for managing Asset Type data.
 */
public interface AssetTypeService {

    /**
     * Retrieves a paginated list of asset types based on the provided pagination request.
     *
     * @param paginationRequest the pagination request containing page number, size, and sorting options
     * @return a Mono emitting a PaginationResponse containing a list of AssetTypeDTO objects
     */
    Mono<PaginationResponse<AssetTypeDTO>> listAssetTypes(PaginationRequest paginationRequest);

    /**
     * Creates a new asset type record based on the provided AssetTypeDTO.
     *
     * @param assetTypeDto the DTO containing details of the asset type to be created
     * @return a Mono emitting the created AssetTypeDTO object
     */
    Mono<AssetTypeDTO> createAssetType(AssetTypeDTO assetTypeDto);

    /**
     * Retrieves the details of an asset type by its unique identifier.
     *
     * @param assetId the unique identifier of the asset type to retrieve
     * @return a Mono emitting the AssetTypeDTO containing details about the specified asset type, or an empty Mono if not found
     */
    Mono<AssetTypeDTO> getAssetType(Long assetId);

    /**
     * Updates the details of an existing asset type by its unique identifier.
     *
     * @param assetId the unique identifier of the asset type to be updated
     * @param assetTypeDto the DTO containing the updated asset type details
     * @return a Mono emitting the updated AssetTypeDTO object if the update is successful
     */
    Mono<AssetTypeDTO> updateAssetType(Long assetId, AssetTypeDTO assetTypeDto);

    /**
     * Deletes an asset type identified by its unique identifier.
     *
     * @param assetId the unique identifier of the asset type to delete
     * @return a Mono signaling completion of the delete operation
     */
    Mono<Void> deleteAssetType(Long assetId);
}