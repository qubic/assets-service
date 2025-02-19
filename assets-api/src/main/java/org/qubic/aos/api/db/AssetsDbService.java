package org.qubic.aos.api.db;

import lombok.extern.slf4j.Slf4j;
import org.qubic.aos.api.db.domain.Asset;
import org.springframework.cache.annotation.Cacheable;

import static org.qubic.aos.api.redis.AssetsCacheManager.CACHE_KEY_ASSET;
import static org.qubic.aos.api.redis.AssetsCacheManager.CACHE_NAME_GET_ASSET;

@Slf4j
public class AssetsDbService {

    private final AssetsRepository assetsRepository;

    public AssetsDbService(AssetsRepository assetsRepository) {
        this.assetsRepository = assetsRepository;
    }

    // we cache because this method gets called often for universe imports
    @Cacheable(value = CACHE_NAME_GET_ASSET, key = CACHE_KEY_ASSET)
    public Asset getOrCreateAsset(String issuer, String asset) {
        log.debug("Getting asset [{}/{}]...", issuer, asset);
        return assetsRepository.findByIssuerAndName(issuer, asset)
                .orElseGet(() -> createAsset(issuer, asset));
    }

    public Asset createAsset(String issuer, String asset) {
        log.info("Creating asset with issuer [{}] and name [{}].", issuer, asset);
        return assetsRepository.save(Asset.builder().issuer(issuer).name(asset).build());
    }

}
