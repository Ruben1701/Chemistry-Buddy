package serializer;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SerializerTest {

    private Serializer serializer = new Serializer();

    @Test
    void serialize() {
        int x;
        try {
            serializer.Serialize("test","/Users/ruben/Desktop/Big Idea/Chemistry-Buddy/src/main/java/serializer/test.ser");
            x = 1;
        } catch (IOException e) {
            e.printStackTrace();
            x = 2;
        }
        assertEquals(1, x);
    }

    @Test
    public void serializeNonExistingFile() throws IOException {
        serializer.Serialize("test","/Users/ruben/Desktop/Big Idea/Chemistry-Buddy/src/main/java/serializer/NotExisting.ser");
        assertTrue(new File("/Users/ruben/Desktop/Big Idea/Chemistry-Buddy/src/main/java/serializer/NotExisting.ser").isFile());
        new File("/Users/ruben/Desktop/Big Idea/Chemistry-Buddy/src/main/java/serializer/NotExisting.ser").delete();
    }
}