package serializer;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Serializer {

    public void Serialize(String toSerialize){
        try {
            FileOutputStream fileout = new FileOutputStream("src/main/java/Serializer/data.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fileout);
            oos.writeObject(toSerialize);
            oos.close();
            fileout.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
