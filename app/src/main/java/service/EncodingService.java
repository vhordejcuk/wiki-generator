package service;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class EncodingService {
    public String decode(String dataIn) {
        return new String(Base64.decodeBase64(dataIn), StandardCharsets.UTF_8);
    }
}
