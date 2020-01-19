package controller;

import javafx.event.ActionEvent;
import serializer.FileWatcher;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;

import model.dictionary.ElementDictionary;
import model.dictionary.Element;
import service.QuizService;
import service.UserInterfaceService;
import websocket.EventClient;
import serializer.LoadSerialized;

import javax.websocket.Session;
import java.util.*;
import java.io.*;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class QuizController implements Initializable {

    private Session session;
    public Label QuestionLbl;
    public Button Quitbtn;
    private boolean quizFinished;

    private final EventClient eventClient = new EventClient();
    private final LoadSerialized loadSerialized = new LoadSerialized();
    private final QuizService quizService = new QuizService();
    private final UserInterfaceService userInterfaceService = new UserInterfaceService();

    private static final String DataSerPath = "/Users/ruben/Desktop/Big Idea/Chemistry-Buddy/src/main/java/serializer/data.ser";
    private static final String UserSerPath = "/Users/ruben/Desktop/Big Idea/Chemistry-Buddy/src/main/java/serializer/user.ser";


    LogManager lgmngr = LogManager.getLogManager();
    Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FileMonitor();
        session = quizService.startQuiz(eventClient);
        String message = null;
        try {
            message = "UserId : " + loadSerialized.LoadQuestion(UserSerPath);
        } catch (IOException e) {
            log.log(Level.SEVERE, (Supplier<String>) e);
        }
        quizService.sendAnswer(eventClient, message, session);
    }

    private void setQuestion(String question) {
        QuestionLbl.setText(question);
    }

    public void ElementClicked(MouseEvent mouseEvent) {
        //TODO quizfinshed can be made more solid
        if (!quizFinished && !QuestionLbl.getText().equals("Joined quiz room waiting on more players")){
            ElementDictionary elementDictionary = new ElementDictionary();
            Dictionary elements = elementDictionary.getElements();
            Element chosenelement = (Element) elements.get(mouseEvent.getPickResult().getIntersectedNode().getId());
            log.log(Level.INFO, chosenelement.Name);

            quizService.sendAnswer(eventClient, chosenelement.Shortname, session);
            setQuestion("Answer Sent");
        }
        else if (quizFinished) {
            setQuestion("Quiz has already finished");
        }
        else if (QuestionLbl.getText().contains("Joined")){
            setQuestion("No question received yet please wait");
        }
    }

    private void FileMonitor() {
        TimerTask task = new FileWatcher(new File(DataSerPath)) {
            protected void onChange(File file) {
                //runlater because UI changes need to be made on the javaFx thread
                Platform.runLater(
                        () -> {
                            try {
                                setQuestion(loadSerialized.LoadQuestion(DataSerPath));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                if (loadSerialized.LoadQuestion(DataSerPath).equals("End of quiz!")){
                                        Quitbtn.setDisable(false);
                                        quizFinished = true;

                                    }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                );
                log.log(Level.INFO, "File " + file.getName() + " has been changed");
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, new Date(), 2000);
    }

    public void leaveGame(ActionEvent actionEvent) throws IOException {
        quizService.exitQuiz(eventClient, session);
        userInterfaceService.changeScreenActionEvent(actionEvent, "Periodiek.fxml");
    }
}
