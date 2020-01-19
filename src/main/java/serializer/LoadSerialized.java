package serializer;

import java.io.*;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import interfaces.iDeserializer;

public class LoadSerialized implements iDeserializer{

    @Override
    public String LoadQuestion(String file) throws IOException {
        LogManager lgmngr = LogManager.getLogManager();
        Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);

        String question;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try
        {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);

            question = (String) ois.readObject();
        } catch (FileNotFoundException f){
            log.log(Level.SEVERE, (Supplier<String>) f);
            question = "file doesnt exist";
        } catch (IOException ioe)
        {
            question = "Error";
            log.log(Level.SEVERE, (Supplier<String>) ioe);

        }
        catch (ClassNotFoundException c)
        {
            question = "Error";
            log.log(Level.SEVERE, (Supplier<String>) c);

        }
        //ClearFile();
        finally {
            Objects.requireNonNull(ois).close();
            Objects.requireNonNull(fis).close();
            log.log(Level.INFO, "Saved to data.ser");

        }
        return question;
    }

    @Override
    public void ClearFile() throws IOException {
        new FileOutputStream("src/main/java/Serializer/data.ser").close();
    }


}
