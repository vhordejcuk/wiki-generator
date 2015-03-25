package servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PingServlet extends AbstractHttpServlet {
    @Override
    protected void process(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        setupForText(response);
        printTextLine(response, "--------------------------------------------");
        printTextLine(response, "!!! ITS ALIVE !!!");
        printTextLine(response, "--------------------------------------------");
        printTextLine(response, "GraphViz version: %s", graphVizService.getGraphVizVersion());
        printTextLine(response, "Maximum heap: %.3f MiB", cacheService.getMaxHeapSizeMiB());
        printTextLine(response, "Cache size: %d", cacheService.getCacheSize());
        printTextLine(response, "Cache requests: %d", cacheService.getCacheRequestCount());
        printTextLine(response, "Cache hit rate: %.1f%%", cacheService.getCacheHitRate() * 100.0);
        printTextLine(response, "Cache miss rate: %.1f%%", cacheService.getCacheMissRate() * 100.0);
        printTextLine(response, "Cache load penalty: %.1f ms", cacheService.getAverageCacheLoadPenaltyMs());
    }
}
