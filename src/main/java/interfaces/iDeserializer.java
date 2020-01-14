package interfaces;

import java.io.IOException;

public interface iDeserializer {

    String LoadQuestion(String file) throws IOException;

    void ClearFile();
}
