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

    public GraphVizService(final CacheService cacheService) {
        this.cacheService = cacheService;
    }

    public byte[] generateGraph(final String source) throws ExecutionException {
        return getFromCacheOrGenerate("graph {" + source + "}");
    }

    public byte[] generateDigraph(final String source) throws ExecutionException {
        return getFromCacheOrGenerate("digraph {" + source + "}");
    }

    private byte[] getFromCacheOrGenerate(final String source) throws ExecutionException {
        final String key = Encoder.getHash(source);

        return cacheService.get(key, new Callable<byte[]>() {
            @Override
            public byte[] call() throws Exception {
                return generate(source);
            }
        });
    }

    private static byte[] generate(final String source) throws IOException, InterruptedException {
        final Process process = new ProcessBuilder("dot", "-Tpng").start();
        final OutputStream osToProcess = process.getOutputStream();
        final PrintWriter toProcess = new PrintWriter(osToProcess);
        toProcess.write(source);
        toProcess.close();
        final ByteArrayOutputStream baos = new ByteArrayOutputStream(Configuration.DEFAULT_IMAGE_SIZE);
        final InputStream fromProcess = process.getInputStream();
        ByteStreams.copy(fromProcess, baos);
        fromProcess.close();
        baos.close();
        process.waitFor();
        return baos.toByteArray();
    }
}
