package service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;

public class PlantUmlService {
    private CacheService cacheService;

    public PlantUmlService(final CacheService cacheService) {
        this.cacheService = cacheService;
    }

    public byte[] generateUmlClass(final String source) throws ExecutionException {
        return getFromCacheOrGenerate("@startuml\n" + source.trim() + "\n@enduml\n");
    }

    public byte[] generateUmlSequence(final String source) throws ExecutionException {
        return getFromCacheOrGenerate("@startuml\n" + source.trim() + "\n@enduml\n");
    }

    public byte[] generateUmlActivity(final String source) throws ExecutionException {
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
}
