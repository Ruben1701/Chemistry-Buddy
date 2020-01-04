package serializer;

import java.io.*;

public class LoadSerialized {

    public String LoadQuestion() {

        String question;
        try
        {
            FileInputStream fis = new FileInputStream("src/main/java/Serializer/data.ser");
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

    public void ClearFile() {
        try {
            new FileOutputStream("src/main/java/Serializer/data.ser").close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
