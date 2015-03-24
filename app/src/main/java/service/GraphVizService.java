package service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import com.google.common.io.ByteStreams;

public class GraphVizService {
    private CacheService cacheService;

    public GraphVizService(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    public byte[] generateGraph(String source) throws ExecutionException {
        return getFromCacheOrGenerate("graph {" + source + "}");
    }

    public byte[] generateDigraph(String source) throws ExecutionException {
        return getFromCacheOrGenerate("digraph {" + source + "}");
    }

    private byte[] getFromCacheOrGenerate(final String source) throws ExecutionException {
        String key = Encoder.getHash(source);

        return cacheService.get(key, new Callable<byte[]>() {
            @Override
            public byte[] call() throws Exception {
                return generate(source);
            }
        });
    }

    private static byte[] generate(String source) throws IOException, InterruptedException {
        Process process = new ProcessBuilder("dot", "-Tpng").start();
        OutputStream osToProcess = process.getOutputStream();
        PrintWriter toProcess = new PrintWriter(osToProcess);
        toProcess.write(source);
        toProcess.close();
        ByteArrayOutputStream baos = new ByteArrayOutputStream(Configuration.DEFAULT_IMAGE_SIZE);
        InputStream fromProcess = process.getInputStream();
        ByteStreams.copy(fromProcess, baos);
        fromProcess.close();
        baos.close();
        process.waitFor();
        return baos.toByteArray();
    }
}
