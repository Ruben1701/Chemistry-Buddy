package service;
import interfaces.iUserInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class UserInterfaceService implements iUserInterface{

    @Override
    public void changeScreenActionEvent(ActionEvent actionEvent, String ScreenName) throws IOException {
        Parent blah = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(ScreenName)));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }

    @Override
    public void changeScreenMouseEvent(MouseEvent mouseEvent, String ScreenName) throws IOException {
        Parent blah = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(ScreenName)));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }
}
