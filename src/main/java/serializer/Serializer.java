package serializer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Objects;

import interfaces.iSerializer;

public class Serializer implements iSerializer {

    @Override
    public void Serialize(String toSerialize, String file) throws IOException {
        FileOutputStream fileout = null;
        ObjectOutputStream oos = null;
        try {
            fileout = new FileOutputStream(file);
            oos = new ObjectOutputStream(fileout);
            oos.writeObject(toSerialize);

        } catch (Exception e) {
            System.out.println(e);
        }
        finally {
            Objects.requireNonNull(oos).close();
            Objects.requireNonNull(fileout).close();
        }
    }

}
