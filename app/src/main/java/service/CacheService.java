package service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.Weigher;

/**
 * Service for caching binary values keyed by a string.
 * It has a maximum size and when the size is exceeded, NRU strategy is used to evict entries.
 */
public class CacheService {
    private final Cache<String, byte[]> cache = CacheBuilder
            .newBuilder()
            .recordStats()
            .maximumWeight(Configuration.MAX_CACHE_SIZE_BYTES)
            .weigher(new Weigher<String, byte[]>() {
                @Override
                public int weigh(final String s, final byte[] bytes) {
                    return s.length() + bytes.length;
                }
            })
            .build();

    /**
     * Returns a value from the cache.
     * If the value is not present, it is obtained using the loader provided.
     *
     * @param key key to obtain
     * @param loaderIfMissing loaded to use if a cache entry is missing
     * @return cache entry value
     * @throws ExecutionException error while obtaining the value
     */
    public byte[] get(final String key, final Callable<byte[]> loaderIfMissing) throws ExecutionException {
        return cache.get(key, loaderIfMissing);
    }

    /**
     * Returns the maximum heap size in megabytes.
     *
     * @return heap size in megabytes
     */
    public double getMaxHeapSizeMiB() {
        return bytesToMegabytes(Runtime.getRuntime().maxMemory());
    }

    /**
     * Returns a number of cache entries.
     *
     * @return number of entries
     */
    public long getCacheSize() {
        return cache.size();
    }

    /**
     * Returns the relative cache hit rate.
     *
     * @return hit rate
     */
    public double getCacheHitRate() {
        return cache.stats().hitRate();
    }

    /**
     * Returns the relative cache miss rate.
     *
     * @return miss rate
     */
    public double getCacheMissRate() {
        return cache.stats().missRate();
    }

    /**
     * Returns the total number of cache requests.
     *
     * @return number of cache requests
     */
    public long getCacheRequestCount() {
        return cache.stats().requestCount();
    }

    /**
     * Returns the average cache load penalty in milliseconds.
     *
     * @return average cache load penalty
     */
    public double getAverageCacheLoadPenaltyMs() {
        return nanosToMillis(cache.stats().averageLoadPenalty());
    }

    private static double bytesToMegabytes(final long value) {
        return value / (1024.0 * 1024.0);
    }

    private static double nanosToMillis(final double value) {
        return value / (1000.0 * 1000.0);
    }
}
