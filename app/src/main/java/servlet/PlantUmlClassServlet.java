package servlet;

import java.util.concurrent.ExecutionException;

public class PlantUmlClassServlet extends AbstractUmlHttpServlet {
    @Override
    protected byte[] process(final String source) throws ExecutionException {
        return plantUmlService.generateUmlClass(source);
    }
}
