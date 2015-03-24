package servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PingServlet extends AbstractHttpServlet {
    @Override
    void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        setupForText(response);
        printTextLine(response, "Hello world!");
    }
}
