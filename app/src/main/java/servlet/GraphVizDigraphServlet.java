package servlet;

import java.util.concurrent.ExecutionException;

public class GraphVizDigraphServlet extends AbstractGraphVizHttpServlet {
    @Override
    protected byte[] process(String source) throws ExecutionException {
        return graphVizService.generateDigraph(source);
    }
}
