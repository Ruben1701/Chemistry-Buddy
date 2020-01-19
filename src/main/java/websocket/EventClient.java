package websocket;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class EventClient {

    private static final  String answerUri = "ws://localhost:8069/ChemistryBuddy/Quiz/";
    LogManager lgmngr = LogManager.getLogManager();
    Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public Session websocket(String messageType){
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try {
            return openConnection(container, messageType);
        } catch (Exception e) {
            log.log(Level.SEVERE, (Supplier<String>) e);
            return null;
        }
    }

    private Session openConnection(WebSocketContainer container, String messageType) {
        String uri = null;
        try {
            if (messageType.equals("quiz")){
                uri = answerUri;
            }
            return container.connectToServer(EventClientSocket.class, new URI(Objects.requireNonNull(uri)));
        } catch (Exception e) {
            log.log(Level.SEVERE, (Supplier<String>) e);
            return null;
        }
    }

    public void sendMessage(String message, Session session) {
        try {
            session.getBasicRemote().sendText(message);
        }
        catch (Exception e)
        {
            log.log(Level.SEVERE, (Supplier<String>) e);
        }
    }

    public void closeConnection(Session session) {
        try {
            session.close();
            log.log(Level.INFO, "Disconnected");
        } catch (IOException e) {
            log.log(Level.SEVERE, (Supplier<String>) e);
        }
    }
}
