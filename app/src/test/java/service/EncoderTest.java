package service;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Constructor;

import org.junit.Test;

public class EncoderTest {
    @Test
    public void testDecode() {
        assertEquals("test", Encoder.decode("test"));
    }

    @Test
    public void testHash() {
        assertEquals(null, Encoder.getHash(null));
        assertEquals("da39a3ee5e6b4b0d3255bfef95601890afd80709", Encoder.getHash(""));
        assertEquals("aaf4c61ddcc5e8a2dabede0f3b482cd9aea9434d", Encoder.getHash("hello"));
        assertEquals("7c211433f02071597741e6ff5a8ea34789abbf43", Encoder.getHash("world"));
    }

    @Test
    public void testPrivateConstructor() throws Exception {
        Constructor<?> constructor = Encoder.class.getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        constructor.newInstance();
    }
}