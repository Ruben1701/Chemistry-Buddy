package gui;

import serializer.FileWatcher;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Dictionary;
import java.util.ResourceBundle;

import dictionary.ElementDictionary;
import dictionary.Element;
import websocket.EventClient;
import serializer.LoadSerialized;

import javax.websocket.Session;
import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class QuizController implements Initializable {

    public Session session;
    public Label QuestionLbl;
    EventClient eventClient = new EventClient();
    LoadSerialized loadSerialized = new LoadSerialized();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FileMonitor();
        session = eventClient.websocket("quiz");
        //eventClient.sendMessage("quiz", session);
        setQuestion("Waiting on Question");
    }

    public void setQuestion(String question) {
        QuestionLbl.setText(question);
    }

    public void ElementClicked(MouseEvent mouseEvent) {
        LogManager lgmngr = LogManager.getLogManager();
        Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);

        ElementDictionary elementDictionary = new ElementDictionary();
        Dictionary elements = elementDictionary.getElements();
        Element chosenelement = (Element) elements.get(mouseEvent.getPickResult().getIntersectedNode().getId());
        log.log(Level.INFO, chosenelement.Name);

        //System.out.println(chosenelement.Name);
        eventClient.sendMessage(chosenelement.Shortname, session);
        setQuestion("Answer Sent");
    }

    public void FileMonitor() {
        LogManager lgmngr = LogManager.getLogManager();
        Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);

        TimerTask task = new FileWatcher(new File("src/main/java/Serializer/data.ser")) {
            protected void onChange(File file) {
                //runlater because UI changes need to be made on the javaFx thread
                Platform.runLater(
                        () -> {
                            //try {
                                setQuestion(loadSerialized.LoadQuestion());
                            //} catch () {
                                //log.log(Level.SEVERE, (Supplier<String>) e);
                                //e.printStackTrace();
                            //}
                        }
                );
                log.log(Level.INFO, "File " + file.getName() + " has been changed");
                //System.out.println( "File "+ file.getName() +" has been changed" );
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, new Date(), 2000);
    }
}
