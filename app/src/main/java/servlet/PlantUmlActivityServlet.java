package servlet;

import java.util.concurrent.ExecutionException;

public class PlantUmlActivityServlet extends AbstractUmlHttpServlet {
    @Override
    protected byte[] process(String source) throws ExecutionException {
        return plantUmlService.generateUmlActivity(source);
    }
}
