package service;

import org.junit.jupiter.api.Test;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import static org.junit.jupiter.api.Assertions.*;

public class ConnectionTest {

    //first start the TestRestService
    @Test
    void testRestConnection(){
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:8090/service/test/available");
        Response response = webTarget.request().get();

        assertEquals(response.readEntity(String.class), "yes");

    }

    @Test
    void testWebsocketConnection(){
        //TODO
    }
}
