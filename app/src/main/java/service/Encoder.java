package service;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

public final class Encoder {
    private Encoder() {
        // NOP
    }

    public static String getHash(String value) {
        return Hashing.sha1().hashBytes(value.getBytes(Charsets.UTF_8)).toString();
    }

    public static String decode(String dataIn) {
        return dataIn;
    }
}
