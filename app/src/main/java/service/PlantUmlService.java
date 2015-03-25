package service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;

/**
 * PlantUML calling service.
 */
public class PlantUmlService {
    private final CacheService cacheService;

    /**
     * Creates a new instance.
     *
     * @param cacheService cache service
     */
    public PlantUmlService(final CacheService cacheService) {
        this.cacheService = cacheService;
    }

    /**
     * Generates UML class diagram.
     *
     * @param source diagram source
     * @return generated image
     * @throws ExecutionException when generating fails
     */
    public byte[] generateUmlClass(final String source) throws ExecutionException {
        return getFromCacheOrGenerate("@startuml\n" + source.trim() + "\n@enduml\n");
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

    private static byte[] generate(final String source) throws IOException {
        final SourceStringReader reader = new SourceStringReader(source);
        final ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream(Configuration.DEFAULT_IMAGE_SIZE);
        reader.generateImage(outputBuffer, new FileFormatOption(FileFormat.PNG, false));
        return outputBuffer.toByteArray();
    }

    /**
     * Generates UML sequence diagram.
     *
     * @param source diagram source
     * @return generated image
     * @throws ExecutionException when generating fails
     */
    public byte[] generateUmlSequence(final String source) throws ExecutionException {
        return getFromCacheOrGenerate("@startuml\n" + source.trim() + "\n@enduml\n");
    }

    /**
     * Generates UML activity diagram.
     *
     * @param source diagram source
     * @return generated image
     * @throws ExecutionException when generating fails
     */
    public byte[] generateUmlActivity(final String source) throws ExecutionException {
        return getFromCacheOrGenerate("@startuml\n" + source.trim() + "\n@enduml\n");
    }
}
