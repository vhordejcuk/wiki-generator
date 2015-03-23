package service;

import org.apache.commons.io.IOUtils;

import java.io.*;

public class GraphVizService {
    public byte[] generateGraph(String source) throws IOException, InterruptedException {
        return generate("graph {" + source + "}");
    }

    public byte[] generateDigraph(String source) throws IOException, InterruptedException {
        return generate("digraph {" + source + "}");
    }

    private byte[] generate(String source) throws IOException, InterruptedException {
        Process process = new ProcessBuilder("dot", "-Tpng").start();
        OutputStream osToProcess = process.getOutputStream();
        PrintWriter toProcess = new PrintWriter(osToProcess);
        toProcess.write(source);
        toProcess.close();
        ByteArrayOutputStream baos = new ByteArrayOutputStream(Configuration.DEFAULT_IMAGE_SIZE);
        InputStream fromProcess = process.getInputStream();
        IOUtils.copy(fromProcess, baos);
        fromProcess.close();
        baos.close();
        process.waitFor();
        return baos.toByteArray();
    }
}
