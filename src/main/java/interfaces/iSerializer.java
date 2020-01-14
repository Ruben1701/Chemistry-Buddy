package interfaces;

import java.io.IOException;

public interface iSerializer {

    void Serialize(String toSerialize, String file) throws IOException;
}
