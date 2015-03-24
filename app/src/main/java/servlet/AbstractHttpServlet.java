package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CacheService;
import service.GraphVizService;
import service.PlantUmlService;

public abstract class AbstractHttpServlet extends HttpServlet {
    protected static final CacheService cacheService = new CacheService();
    protected static final GraphVizService graphVizService = new GraphVizService(cacheService);
    protected static final PlantUmlService plantUmlService = new PlantUmlService(cacheService);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            process(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    abstract void process(HttpServletRequest request, HttpServletResponse response) throws Exception;

    protected void setupForPNG(HttpServletResponse response) {
        response.setContentType("image/png");
        response.setCharacterEncoding("utf-8");
    }

    protected void setupForSVG(HttpServletResponse response) {
        response.setContentType("image/svg+xml");
        response.setCharacterEncoding("utf-8");
    }

    protected void setupForText(HttpServletResponse response) {
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
    }

    protected void printTextLine(HttpServletResponse response, String text) throws IOException {
        response.getWriter().println(text);
    }
}
