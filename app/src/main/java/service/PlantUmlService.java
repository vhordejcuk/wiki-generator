package service;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PlantUmlService {
    public byte[] generateGraph(String source) throws IOException, InterruptedException {
        SourceStringReader reader = new SourceStringReader("@startuml\n" + source.trim() + "\n@enduml\n");
        ByteArrayOutputStream baos = new ByteArrayOutputStream(Configuration.DEFAULT_IMAGE_SIZE);
        reader.generateImage(baos, new FileFormatOption(FileFormat.PNG, false));
        return baos.toByteArray();
    }
}
