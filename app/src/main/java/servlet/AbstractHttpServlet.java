package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CacheService;
import service.GraphVizService;
import service.PlantUmlService;

/**
 * Abstract base class for all servlets.
 */
abstract class AbstractHttpServlet extends HttpServlet {
    protected static final CacheService cacheService = new CacheService();
    protected static final GraphVizService graphVizService = new GraphVizService(cacheService);
    protected static final PlantUmlService plantUmlService = new PlantUmlService(cacheService);

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        try {
            process(request, response);
        } catch (final Exception e) {
            throw new ServletException(e);
        }
    }

    /**
     * Processes the request to a response throwing error.
     *
     * @param request request
     * @param response response
     * @throws Exception when something goes bad
     */
    protected abstract void process(HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * Sets content type headers for PNG.
     *
     * @param response response
     */
    protected void setupForPNG(final HttpServletResponse response) {
        response.setContentType("image/png");
        response.setCharacterEncoding("utf-8");
    }

    /**
     * Sets content type headers for plain text in UTF-8.
     *
     * @param response response
     */
    protected void setupForText(final HttpServletResponse response) {
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
    }

    /**
     * Prints a single text line.
     *
     * @param response response
     * @param text text (can have format)
     * @param arguments format arguments (optional)
     * @throws IOException when printing fails
     */
    protected void printTextLine(final HttpServletResponse response, final String text, final Object... arguments) throws IOException {
        response.getWriter().println(String.format(text, arguments));
    }
}
