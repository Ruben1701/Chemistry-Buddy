package serializer;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class LoadSerializedTest {

    private LoadSerialized loadSerializedializer = new LoadSerialized();

    @Test
    void loadQuestion() throws IOException {
        assertTrue(loadSerializedializer.LoadQuestion("/Users/ruben/Desktop/Big Idea/Chemistry-Buddy/src/main/java/serializer/test.ser").contains("test"));
    }

    @Test
    public void loadquestionNonExistingFile() {
        assertThrows(NullPointerException.class, () -> {
            loadSerializedializer.LoadQuestion("/Users/ruben/Desktop/Big Idea/Chemistry-Buddy/src/main/java/serializer/NotExisting.ser");
        });

    }

    @Test
    void clearFile() {
        //TODO
    }
}