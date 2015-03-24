package servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PingServlet extends AbstractHttpServlet {
    @Override
    void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        setupForText(response);
        printTextLine(response, "--------------------------------------------");
        printTextLine(response, "I am alive!");
        printTextLine(response, "--------------------------------------------");
        printTextLine(response, "Maximum heap: %.3f MiB", cacheService.getMaxHeapSizeMiB());
        printTextLine(response, "Cache size: ", cacheService.getCacheSize());
        printTextLine(response, "Cache requests: %.3f", cacheService.getCacheRequestCount());
        printTextLine(response, "Cache hit rate: %.1f %%", cacheService.getCacheHitRate() * 100.0);
        printTextLine(response, "Cache miss rate: %.1f %%", cacheService.getCacheMissRate() * 100.0);
        printTextLine(response, "Cache load penalty: %.1f ms", cacheService.getAverageCacheLoadPenaltyMs());
    }
}
