package servlet;

import java.io.ByteArrayInputStream;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.io.ByteStreams;

import service.Encoder;

public abstract class AbstractUmlHttpServlet extends AbstractHttpServlet {
    @Override
    void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        setupForPNG(response);
        String dataEncoded = request.getParameter("data");
        String data = Encoder.decode(dataEncoded);
        ByteArrayInputStream bais = new ByteArrayInputStream(process(data));
        ByteStreams.copy(bais, response.getOutputStream());
        response.flushBuffer();
    }

    protected abstract byte[] process(String source) throws ExecutionException;
}
