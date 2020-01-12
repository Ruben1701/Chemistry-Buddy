package service;
import interfaces.iLogin;
import model.DTO.LoginDTO;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class LoginService implements iLogin {
    @Override
    public String login(LoginDTO loginDTO) {
    Client client = ClientBuilder.newClient();


        //Setting the post method url to the client
        WebTarget webTarget = client.target("http://localhost:8090/auth/login");

        //Add key-value pair into the form object
        Form form = new Form();
        form.param("Email", loginDTO.getUsername());
        form.param("Password", loginDTO.getPassword());

        //Send the form object along with the post call
        Response response = webTarget.request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
        if (response.getStatus() == 200){
            String stringJson = response.readEntity(String.class);
            stringJson = stringJson.replaceAll("\\D+","");

            return stringJson;
        }

        else {
            return null;
        }



//        System.out.println("Response code: " +  response.getStatus());
//        System.out.println("Response value: " + stringJson);
//        System.out.println(player.getId());


    }
}
