package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import serializer.Serializer;
import service.LoginService;
import service.UserInterfaceService;
import model.DTO.LoginDTO;

import java.io.IOException;

public class LoginController {


    public TextField EmailTxt;
    public TextField PasswordTxt;
    public final UserInterfaceService userInterfaceService = new UserInterfaceService();

    public void LoginClicked(ActionEvent actionEvent) throws IOException {
        LoginService loginService = new LoginService();
        LoginDTO loginDTO = new LoginDTO(EmailTxt.getText(), PasswordTxt.getText());
        //LoginDTO loginDTO = new LoginDTO(EmailTxt.getText(), PasswordTxt.getText());
        if (loginService.login(loginDTO) != null){
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Periodiek.fxml"));
            Pane pane = (Pane) loader.load();
            Controller controller = loader.getController();
            Serializer serializer = new Serializer();
            serializer.Serialize(loginService.login(loginDTO), "/Users/ruben/Desktop/Big Idea/Chemistry-Buddy/src/main/java/serializer/user.ser");
            //controller.setCurrentPlayer(loginService.login(loginDTO));
            userInterfaceService.changeScreenActionEvent(actionEvent, "Periodiek.fxml");
        }
    }

    public void NoAccountClicked(MouseEvent mouseEvent) throws IOException {
        userInterfaceService.changeScreenMouseEvent(mouseEvent, "RegisterScreen.fxml");
    }
}
