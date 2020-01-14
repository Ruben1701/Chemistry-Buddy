package serializer;

import java.io.*;
import java.util.Objects;

import interfaces.iDeserializer;

public class LoadSerialized implements iDeserializer{

    @Override
    public String LoadQuestion(String file) throws IOException {

        String question;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try
        {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);

            question = (String) ois.readObject();
        }
        catch (IOException ioe)
        {
            question = "Error";
            ioe.printStackTrace();
        }
        catch (ClassNotFoundException c)
        {
            question = "Error";
            System.out.println("Class not found");
            c.printStackTrace();
        }
        //ClearFile();
        finally {
            Objects.requireNonNull(ois).close();
            Objects.requireNonNull(fis).close();
        }
        return question;
    }

    @Override
    public void ClearFile() {
        try {
            new FileOutputStream("src/main/java/Serializer/data.ser").close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
