package GUI;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Dictionary;
import java.util.ResourceBundle;

import Dictionary.ElementDictionary;
import Dictionary.Element;
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
        System.out.println(chosenelement.Name);
        eventClient.sendMessage(chosenelement.Shortname, session);

    }


    public void testclick(MouseEvent mouseEvent) {
        session = eventClient.websocket("answer");
        //eventClient.sendMessage("quiz", session);
    }
}
