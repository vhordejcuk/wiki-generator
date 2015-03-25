package service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import com.google.common.io.ByteStreams;

import net.sourceforge.plantuml.cucadiagram.dot.GraphvizUtils;

/**
 * GraphViz calling service.
 */
public class GraphVizService {
    private final CacheService cacheService;

    /**
     * Creates a new instance.
     *
     * @param cacheService cache service
     */
    public GraphVizService(final CacheService cacheService) {
        this.cacheService = cacheService;
    }

    /**
     * Returns the GraphViz version or some error description.
     *
     * @return version or error message
     */
    public String getGraphVizVersion() {
        try {
            return GraphvizUtils.dotVersion();
        } catch (IOException e) {
            return "Error: " + e.getMessage();
        } catch (InterruptedException e) {
            return "Error: " + e.getMessage();
        }
    }

    /**
     * Checks if GraphViz is installed.
     *
     * @return TRUE if the installation is valid, FALSE otherwise
     */
    public boolean isGraphVizInstalled() {
        return getGraphVizVersion().toLowerCase(Locale.ROOT).contains("dot - graphviz version");
    }

    /**
     * Generates a graph from the given source.
     *
     * @param source graph source
     * @return image of a graph (PNG)
     * @throws ExecutionException error while generating
     */
    public byte[] generateGraph(final String source) throws ExecutionException {
        return generateOrUseCache("graph {" + source + "}");
    }

    /**
     * Generates a digraph from the given source.
     *
     * @param source digraph source
     * @return image of a digraph (PNG)
     * @throws ExecutionException error while generating
     */
    public byte[] generateDigraph(final String source) throws ExecutionException {
        return generateOrUseCache("digraph {" + source + "}");
    }

    private byte[] generateOrUseCache(final String source) throws ExecutionException {
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
        final ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream(Configuration.DEFAULT_IMAGE_SIZE);
        final InputStream fromProcess = process.getInputStream();
        ByteStreams.copy(fromProcess, outputBuffer);
        fromProcess.close();
        process.waitFor();
        return outputBuffer.toByteArray();
    }
}
