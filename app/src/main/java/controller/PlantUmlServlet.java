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
import service.PlantUmlService;

import java.io.IOException;

@Controller
@RequestMapping("/uml")
public class PlantUmlServlet {
    @Autowired
    private EncodingService encodingService;
    @Autowired
    private PlantUmlService plantUmlService;

    @RequestMapping("/class/{dataIn}")
    ResponseEntity<byte[]> classDiagram(@PathVariable String dataIn) throws IOException, InterruptedException {
        byte[] dataOut = plantUmlService.generateGraph(encodingService.decode(dataIn));
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<byte[]>(dataOut, headers, HttpStatus.CREATED);
    }
}
