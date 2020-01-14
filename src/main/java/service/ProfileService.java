package service;

import com.google.gson.Gson;
import interfaces.iProfile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Achievement;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import serializer.LoadSerialized;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ProfileService implements iProfile {

    private ArrayList<Achievement> allachievements = new ArrayList<>();

    @Override
    public ObservableList<Achievement> getAllAchievements(){
        LogManager lgmngr = LogManager.getLogManager();
        Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);
        String query = "http://localhost:8091/achievement/getallachievements";
        log.log(Level.INFO, "[Query] : " + query);

        HttpGet httpGet = new HttpGet(query);

        // Perform the query

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpGet)) {

            log.log(Level.INFO, "[Status Line] : " + response.getStatusLine());

            HttpEntity entity = response.getEntity();
            final String entityString = EntityUtils.toString(entity);

            log.log(Level.INFO, "[Entity] : " + entityString);

            Gson gson = new Gson();

            allachievements = gson.fromJson(entityString, allachievements.getClass());

            return FXCollections.observableArrayList(allachievements);
        } catch (IOException e) {

            log.log(Level.SEVERE, "IOException : " + e.toString());
        }
        return null;
    }


    public ObservableList<Achievement> getUserAchievements() throws ParseException {
        Client client = ClientBuilder.newClient();


        WebTarget webTarget = client.target("http://localhost:8091/achievement/getuserachievements");

        Form form = new Form();
        LoadSerialized loadSerialized = new LoadSerialized();
        try {
            form.param("UserId", loadSerialized.LoadQuestion("/Users/ruben/Desktop/Big Idea/Chemistry-Buddy/src/main/java/serializer/user.ser"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Response response = webTarget.request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
        if (response.getStatus() == 200){

            String stringJson = response.readEntity(String.class);

            JSONParser parser = new JSONParser();
            JSONArray fullArray = (JSONArray) parser.parse(stringJson);
            JSONArray ins = new JSONArray();

            for (Object value : fullArray) {
                JSONObject o = (JSONObject) value;
                ins.add(o.get("Achievement_Id"));
            }

            JSONArray jsonArray = new JSONArray();

            for (Object in : ins) {

                jsonArray.add(getAchievementInfo(in));

            }


            return FXCollections.observableArrayList(jsonArray);
        }
        return null;
    }

    public JSONObject getAchievementInfo(Object in) throws ParseException {
        Client client = ClientBuilder.newClient();
        JSONParser parser = new JSONParser();
        WebTarget target = client.target("http://localhost:8091/achievement/getachievement");
        Form form1 = new Form();
        form1.param("AchievementId", in.toString());
        Response response = target.request().post(Entity.entity(form1, MediaType.APPLICATION_FORM_URLENCODED));

        String achievementJson = response.readEntity(String.class);
        System.out.println(achievementJson);

        response.close();
        return (JSONObject) parser.parse(achievementJson);
    }
}
