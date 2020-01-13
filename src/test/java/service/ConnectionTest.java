package service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import websocket.EventClient;
import javax.websocket.Session;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import static org.junit.jupiter.api.Assertions.*;

public class ConnectionTest {

    private static Session session;
    private EventClient eventClient = new EventClient();

    //first start the TestRestService
    @Test
    void testRestConnection(){
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:8090/service/test/available");
        Response response = webTarget.request().get();

        assertEquals("yes", response.readEntity(String.class));

    }

    //first start the websocketserver
    @Test
    void testWebsocketConnection(){
        session = eventClient.websocket("quiz");
        assertNotNull(session);
    }

    @Test
    void closeConnection(){
        eventClient.closeConnection(session);
        //assertNull(session);
    }

    void nothing(){

    }


}
