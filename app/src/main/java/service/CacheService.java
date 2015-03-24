package service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.Weigher;

public class CacheService {
    private final Cache<String, byte[]> cache;

    public CacheService() {
        this.cache = CacheBuilder
                .newBuilder()
                .maximumWeight(Configuration.MAX_CACHE_SIZE_BYTES)
                .weigher(new Weigher<String, byte[]>() {
                    @Override
                    public int weigh(final String s, final byte[] bytes) {
                        return s.length() + bytes.length;
                    }
                })
                .build();
    }

    public byte[] get(final String key, final Callable<byte[]> loaderIfMissing) throws ExecutionException {
        return cache.get(key, loaderIfMissing);
    }
}
