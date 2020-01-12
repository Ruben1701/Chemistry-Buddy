package serializer;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import interfaces.iSerializer;

public class Serializer implements iSerializer {

    @Override
    public void Serialize(String toSerialize, String file){
        try {
            FileOutputStream fileout = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fileout);
            oos.writeObject(toSerialize);
            oos.close();
            fileout.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
