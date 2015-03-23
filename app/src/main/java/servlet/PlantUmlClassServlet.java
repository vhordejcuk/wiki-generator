package servlet;

import org.apache.commons.io.IOUtils;
import service.EncodingService;
import service.PlantUmlService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PlantUmlClassServlet extends HttpServlet {
    private EncodingService encodingService = new EncodingService();
    private PlantUmlService plantUmlService = new PlantUmlService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/png");
        String dataEncoded = req.getParameter("data");
        String data = encodingService.decode(dataEncoded);
        try {
            IOUtils.write(plantUmlService.generateGraph(data), resp.getOutputStream());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        resp.flushBuffer();
    }
}
