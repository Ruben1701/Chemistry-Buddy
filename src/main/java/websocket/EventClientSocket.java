package websocket;

import javax.websocket.*;

import serializer.Serializer;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@ClientEndpoint
public class EventClientSocket implements java.io.Serializable{

    Serializer serializer = new Serializer();

    @OnOpen
    public void onWebSocketConnect() {

        LogManager lgmngr = LogManager.getLogManager();
        Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);
        log.log(Level.INFO, "[Connected]");
    }

    @OnMessage
    public void onWebSocketMessage(String message) {
        LogManager lgmngr = LogManager.getLogManager();
        Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);
        log.log(Level.INFO, "[Received]: " + message);
        serializer.Serialize(message, "src/main/java/Serializer/data.ser");
    }

    @OnClose
    public void onWebSocketClose(CloseReason reason) {
        LogManager lgmngr = LogManager.getLogManager();
        Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);
        String msg = "[Closed]: " + reason;
        log.log(Level.WARNING, msg);
    }
    @OnError
    public void onWebSocketError(Throwable cause) {
        LogManager lgmngr = LogManager.getLogManager();
        Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);
        log.log(Level.SEVERE, "[ERROR]: " + cause.getMessage());
    }
}

