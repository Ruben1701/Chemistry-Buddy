package websocket;

import javax.websocket.*;

import serializer.Serializer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@ClientEndpoint
public class EventClientSocket implements java.io.Serializable{

    private final Serializer serializer = new Serializer();
    LogManager lgmngr = LogManager.getLogManager();
    Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @OnOpen
    public void onWebSocketConnect() {
        log.log(Level.INFO, "[Connected]");
    }

    @OnMessage
    public void onWebSocketMessage(String message) throws IOException {
        log.log(Level.INFO, "[Received]: " + message);
        serializer.Serialize(message, "src/main/java/Serializer/data.ser");
    }

    @OnClose
    public void onWebSocketClose(CloseReason reason) {
        String msg = "[Closed]: " + reason;
        log.log(Level.WARNING, msg);
    }
    @OnError
    public void onWebSocketError(Throwable cause) {
        log.log(Level.SEVERE, "[ERROR]: " + cause.getMessage());
    }
}

