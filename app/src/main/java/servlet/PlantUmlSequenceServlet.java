package servlet;

import java.util.concurrent.ExecutionException;

public class PlantUmlSequenceServlet extends AbstractUmlHttpServlet {
    @Override
    protected byte[] process(final String source) throws ExecutionException {
        return plantUmlService.generateUmlSequence(source);
    }
}
