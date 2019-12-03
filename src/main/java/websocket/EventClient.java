package websocket;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Logger;

public class EventClient {

    private static final  String answerUri = "ws://localhost:8069/ChemistryBuddy/Answer/";

    public Session websocket(String messageType){
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try {
            // Attempt Connect
            Session session = openConnection(container, messageType);
            // Close session
            //Thread.sleep(10000);
            //closeConnection(session);
            return session;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Session openConnection(WebSocketContainer container, String messageType) {
        String uri = null;
        try {
            if (messageType.equals("answer")){
                uri = answerUri;
            }
            Session session = container.connectToServer(EventClientSocket.class, new URI(uri));
            return session;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void sendMessage(String message, Session session) {
        try {
            session.getBasicRemote().sendText(message);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void closeConnection(Session session) {
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
