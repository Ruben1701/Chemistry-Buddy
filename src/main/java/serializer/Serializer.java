package serializer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import interfaces.iSerializer;

public class Serializer implements iSerializer {

    LogManager lgmngr = LogManager.getLogManager();
    Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Override
    public void Serialize(String toSerialize, String file) throws IOException {
        FileOutputStream fileout = null;
        ObjectOutputStream oos = null;
        try {
            fileout = new FileOutputStream(file);
            oos = new ObjectOutputStream(fileout);
            oos.writeObject(toSerialize);

        } catch (Exception e) {
            log.log(Level.SEVERE, (Supplier<String>) e);
        }
        finally {
            Objects.requireNonNull(oos).close();
            Objects.requireNonNull(fileout).close();

        }
    }

}
