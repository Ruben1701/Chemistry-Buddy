package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Dictionary;
import java.util.ResourceBundle;

import dictionary.ElementDictionary;
import dictionary.Element;
import javafx.stage.Stage;
import websocket.EventClient;

import javax.websocket.Session;

public class Controller implements Initializable {

    public TextArea ElementInfotxt;
    public Element element;
    public Session session;
    EventClient eventClient = new EventClient();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void ElementClicked(MouseEvent mouseEvent) {
        ElementDictionary elementDictionary = new ElementDictionary();
        Dictionary elements = elementDictionary.getElements();
        Element chosenelement = (Element)elements.get(mouseEvent.getPickResult().getIntersectedNode().getId());

        System.out.println("test");
        ElementInfotxt.setText("Shortname: " + chosenelement.Shortname + "\n" + "Name: " + chosenelement.Name + "\n" + "Molecular Mass: " + chosenelement.Molecular_Mass + "\n");
    }

    public void GoToProfile(ActionEvent actionEvent) {
    }

    public void Exit(ActionEvent actionEvent) throws IOException {
        //System.exit(0);
        Parent blah = FXMLLoader.load(getClass().getClassLoader().getResource("LoginScreen.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }

    public void StartQuiz(ActionEvent actionEvent) throws IOException {
        Parent blah = FXMLLoader.load(getClass().getClassLoader().getResource("QuizScreen.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }
}
