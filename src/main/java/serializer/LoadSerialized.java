package serializer;

import java.io.*;
import interfaces.iDeserializer;

public class LoadSerialized implements iDeserializer{

    @Override
    public String LoadQuestion(String file) {

        String question;
        try
        {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            question = (String) ois.readObject();

            ois.close();
            fis.close();
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
