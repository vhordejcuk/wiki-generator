package servlet;

import java.util.concurrent.ExecutionException;

public class GraphVizGraphServlet extends AbstractGraphVizHttpServlet {
    @Override
    protected byte[] process(String source) throws ExecutionException {
        return graphVizService.generateGraph(source);
    }
}
