package servlet;

import java.util.concurrent.ExecutionException;

public class PlantUmlSequenceServlet extends AbstractUmlHttpServlet {
    @Override
    protected byte[] process(String source) throws ExecutionException {
        return plantUmlService.generateUmlSequence(source);
    }
}
