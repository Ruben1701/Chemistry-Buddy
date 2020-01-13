package interfaces;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;


import java.io.IOException;

public interface iUserInterface {
    void changeScreenActionEvent(ActionEvent actionEvent, String ScreenName) throws IOException;
    void changeScreenMouseEvent(MouseEvent mouseEvent, String ScreenName) throws IOException;
}
