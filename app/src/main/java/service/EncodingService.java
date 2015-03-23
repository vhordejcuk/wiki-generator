package service;

import net.sourceforge.plantuml.code.Base64Coder;

public class EncodingService {
    public String decode(String dataIn) {
        return Base64Coder.decodeString(dataIn);
    }
}
