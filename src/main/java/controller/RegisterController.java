package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.DTO.RegisterDTO;
import service.RegisterService;
import service.UserInterfaceService;

import java.io.IOException;


public class RegisterController {


    public TextField EmailTbutxt;
    public TextField PasswordTxt;
    public Text AlreadyRegisterredLink;

    public void RegisterClicked(ActionEvent actionEvent) {
        RegisterDTO registerDTO = new RegisterDTO(EmailTbutxt.getText(), PasswordTxt.getText());
        RegisterService registerService = new RegisterService();
        registerService.register(registerDTO);
    }

    public void AlreadyRegisteredClicked(MouseEvent mouseEvent) throws IOException {
        UserInterfaceService userInterfaceService = new UserInterfaceService();
        userInterfaceService.changeScreenMouseEvent(mouseEvent, "LoginScreen.fxml");

    }
}
