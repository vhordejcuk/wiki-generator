package servlet;

import java.util.concurrent.ExecutionException;

public class GraphVizDigraphServlet extends AbstractGraphVizHttpServlet {
    @Override
    protected byte[] process(final String source) throws ExecutionException {
        return graphVizService.generateDigraph(source);
    }
}
