package servlet;

import java.util.concurrent.ExecutionException;

public class PlantUmlActivityServlet extends AbstractUmlHttpServlet {
    @Override
    protected byte[] process(final String source) throws ExecutionException {
        return plantUmlService.generateUmlActivity(source);
    }
}
