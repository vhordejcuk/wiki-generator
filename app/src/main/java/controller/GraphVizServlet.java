package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import service.EncodingService;
import service.GraphVizService;

import java.io.IOException;

@Controller
@RequestMapping("/gv")
public class GraphVizServlet {
    @Autowired
    private GraphVizService graphVizService;
    @Autowired
    private EncodingService encodingService;

    @RequestMapping("/graph/{dataIn}")
    ResponseEntity<byte[]> graph(@PathVariable String dataIn) throws IOException, InterruptedException {
        byte[] dataOut = graphVizService.generateGraph(encodingService.decode(dataIn));
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<byte[]>(dataOut, headers, HttpStatus.CREATED);
    }

    @RequestMapping("/digraph/{dataIn}")
    ResponseEntity<byte[]> digraph(@PathVariable String dataIn) throws IOException, InterruptedException {
        byte[] dataOut = graphVizService.generateDigraph(encodingService.decode(dataIn));
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<byte[]>(dataOut, headers, HttpStatus.CREATED);
    }
}
