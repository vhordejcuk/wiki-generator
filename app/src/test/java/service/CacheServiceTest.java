package service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.Callable;

import org.junit.Before;
import org.junit.Test;

public class CacheServiceTest {
    private CacheService service;

    @Before
    public void setUp() throws Exception {
        service = new CacheService();
    }

    @Test
    public void testGet() throws Exception {
        final byte[] value = new byte[]{0, 1, 2, 3, 4, 5, 6, 7};

        assertEquals(0L, service.getCacheSize());

        assertArrayEquals(value, service.get("key1", new Callable<byte[]>() {
            @Override
            public byte[] call() throws Exception {
                return value;
            }
        }));

        assertEquals(1L, service.getCacheSize());
    }

    @Test
    public void testGetMaxHeapSizeMiB() throws Exception {
        assertTrue(service.getMaxHeapSizeMiB() > 0);
    }

    @Test
    public void testGetCacheSize() throws Exception {
        assertEquals(0L, service.getCacheSize());
    }

    @Test
    public void testGetCacheHitRate() throws Exception {
        assertEquals(1.0, service.getCacheHitRate(), 0.001);
    }

    @Test
    public void testGetCacheMissRate() throws Exception {
        assertEquals(0.0, service.getCacheMissRate(), 0.001);
    }

    @Test
    public void testGetCacheRequestCount() throws Exception {
        assertEquals(0L, service.getCacheRequestCount());
    }

    @Test
    public void testGetAverageCacheLoadPenaltyMs() throws Exception {
        assertEquals(0.0, service.getAverageCacheLoadPenaltyMs(), 0.001);
    }
}