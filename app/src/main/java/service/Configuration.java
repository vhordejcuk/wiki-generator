package service;

/**
 * Various constants.
 */
interface Configuration {
    /**
     * default buffer size for images generated
     */
    int DEFAULT_IMAGE_SIZE = 2 * 1024;
    /**
     * maximum cache size in bytes (approximate)
     */
    long MAX_CACHE_SIZE_BYTES = 128 * 1024 * 1024;
}
