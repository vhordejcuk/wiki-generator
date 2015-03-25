package service;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

/**
 * Various string functions.
 */
public final class Encoder {
    private Encoder() {
        // NOP
    }

    /**
     * Returns a hash of a string.
     * NULL is returned back.
     *
     * @param value input string
     * @return a hash
     */
    public static String getHash(final String value) {
        if (value == null) {
            return null;
        }

        return Hashing.sha1().hashBytes(value.getBytes(Charsets.UTF_8)).toString();
    }

    /**
     * Decodes a string from URL parameter, as sent from a client.
     * (Currently does not use any form of encoding.)
     *
     * @param value encoded string
     * @return decoded string
     */
    public static String decode(final String value) {
        return value;
    }
}
