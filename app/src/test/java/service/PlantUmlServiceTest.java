package service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Test;

public class PlantUmlServiceTest {
    private PlantUmlService service;

    @Before
    public void setUp() {
        service = new PlantUmlService(new CacheService());
    }

    @Test
    public void testGenerateUmlClass() throws Exception {
        final byte[] data = service.generateUmlClass("" +
                        "class A extends B {}"
        );
        assertReadablePngImage(data);
    }

    @Test
    public void testGenerateUmlSequence() throws Exception {
        final byte[] data = service.generateUmlSequence("" +
                        "Alice -> Bob: Authentication Request\n" +
                        "Bob --> Alice: Authentication Response\n" +
                        "\n" +
                        "Alice -> Bob: Another authentication Request\n" +
                        "Alice <-- Bob: another authentication Response"
        );
        assertReadablePngImage(data);
    }

    @Test
    public void testGenerateUmlActivity() throws Exception {
        final byte[] data = service.generateUmlActivity("" +
                        "(*) --> \"First Activity\"\n" +
                        "-->[You can put also labels] \"Second Activity\"\n" +
                        "--> (*)"
        );
        assertReadablePngImage(data);
    }

    static void assertReadablePngImage(final byte[] data) throws IOException {
        assertNotNull(data);
        assertTrue(data.length > 0);
        final Image image = ImageIO.read(new ByteArrayInputStream(data));
        assertTrue(image.getWidth(null) > 0);
        assertTrue(image.getHeight(null) > 0);
    }
}