package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Dictionary;
import java.util.ResourceBundle;

import model.dictionary.ElementDictionary;
import model.dictionary.Element;
import javafx.scene.layout.Pane;
import model.Player;
import service.UserInterfaceService;


public class Controller implements Initializable {

    public TextArea ElementInfotxt;
    private Player currentPlayer;
    final UserInterfaceService userInterfaceService = new UserInterfaceService();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void ElementClicked(MouseEvent mouseEvent) {
        ElementDictionary elementDictionary = new ElementDictionary();
        Dictionary elements = elementDictionary.getElements();
        Element chosenelement = (Element)elements.get(mouseEvent.getPickResult().getIntersectedNode().getId());
        ElementInfotxt.setText("Shortname: " + chosenelement.Shortname + "\n" + "Name: " + chosenelement.Name + "\n" + "Molecular Mass: " + chosenelement.Molecular_Mass + "\n");
    }

    public void GoToProfile(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ProfileScreen.fxml"));
        Pane pane = (Pane) loader.load();

        ProfileController controller = loader.getController();
        //controller.setCurrentPlayer(currentPlayer);
        userInterfaceService.changeScreenActionEvent(actionEvent, "ProfileScreen.fxml");

    }

    public void Exit(ActionEvent actionEvent) throws IOException {
        //System.exit(0)
        userInterfaceService.changeScreenActionEvent(actionEvent, "LoginScreen.fxml");
    }

    public void StartQuiz(ActionEvent actionEvent) throws IOException {
        userInterfaceService.changeScreenActionEvent(actionEvent, "QuizScreen.fxml");
    }
}
