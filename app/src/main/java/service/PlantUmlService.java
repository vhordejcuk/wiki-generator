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

    public PlantUmlService(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    public byte[] generateUmlClass(String source) throws ExecutionException {
        return getFromCacheOrGenerate("@startuml\n" + source.trim() + "\n@enduml\n");
    }

    public byte[] generateUmlSequence(String source) throws ExecutionException {
        return getFromCacheOrGenerate("@startuml\n" + source.trim() + "\n@enduml\n");
    }

    public byte[] generateUmlActivity(String source) throws ExecutionException {
        return getFromCacheOrGenerate("@startuml\n" + source.trim() + "\n@enduml\n");
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

    private static byte[] generate(String source) throws IOException {
        SourceStringReader reader = new SourceStringReader(source);
        ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream(Configuration.DEFAULT_IMAGE_SIZE);
        reader.generateImage(outputBuffer, new FileFormatOption(FileFormat.PNG, false));
        return outputBuffer.toByteArray();
    }
}
