package servlet;

import java.io.ByteArrayInputStream;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.io.ByteStreams;

import service.Encoder;

/**
 * Abstract base class for GraphViz servlets.
 */
abstract class AbstractGraphVizHttpServlet extends AbstractHttpServlet {
    @Override
    protected void process(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        setupForPNG(response);
        final String dataEncoded = request.getParameter("data");
        final String data = Encoder.decode(dataEncoded);
        final ByteArrayInputStream bais = new ByteArrayInputStream(process(data));
        ByteStreams.copy(bais, response.getOutputStream());
        response.flushBuffer();
    }

    protected abstract byte[] process(String source) throws ExecutionException;
}
