package servlet;

import org.apache.commons.io.IOUtils;
import service.EncodingService;
import service.GraphVizService;

import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GraphVizGraphServlet extends HttpServlet {
    private EncodingService encodingService = new EncodingService();
    private GraphVizService graphVizService = new GraphVizService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/png");
        String dataEncoded = req.getParameter("data");
        String data = encodingService.decode(dataEncoded);
        try {
            IOUtils.write(graphVizService.generateGraph(data), resp.getOutputStream());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        resp.flushBuffer();
    }
}
