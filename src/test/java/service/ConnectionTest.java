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

class ConnectionTest {

    private static Session session;
    private static final EventClient eventClient = new EventClient();

    @BeforeAll
    static void before(){
        session = eventClient.websocket("quiz");
    }
    //first start the TestRestService
    @Test
    void testRestConnection(){
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:8092/service/test/available");
        Response response = webTarget.request().get();

        if(response != null){
            assertEquals("yes", response.readEntity(String.class));
        }
    }

    //first start the websocketserver
    @Test
    void testWebsocketConnection(){
        Session session = eventClient.websocket("quiz");
        assertNotNull(session);
        eventClient.closeConnection(session);
    }

    @Test
    void closeConnection(){
        int x;
        try{
            x = 1;
            eventClient.closeConnection(session);
        }
        catch (Exception e){
            x = 2;
        }
        assertEquals(1, x);
    }
}
