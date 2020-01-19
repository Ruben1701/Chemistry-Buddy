package interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface iDeserializer {

    String LoadQuestion(String file) throws IOException;

    void ClearFile() throws IOException;
}
