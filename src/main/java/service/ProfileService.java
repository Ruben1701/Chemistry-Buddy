package service;

import com.google.gson.Gson;
import interfaces.iProfile;
import model.Achievement;
import model.AchievementOld;
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
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ProfileService implements iProfile {

    LogManager lgmngr = LogManager.getLogManager();
    Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);


    @Override
    public Achievement[] getAllAchievements(){

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

            return gson.fromJson(entityString, Achievement[].class);
        } catch (IOException e) {

            log.log(Level.SEVERE, "IOException : " + e.toString());
        }
        return null;
    }

    public List<String> toListConverter(boolean All) throws ParseException {

        List<String> AchievementStrings = new ArrayList<>();
        Achievement achievements[];
        if (All){
            achievements = getAllAchievements();
        }
        else {
            achievements = getUserAchievements();

        }
        for (Achievement achievement: achievements) {
            AchievementStrings.add(achievement.getAchievement_Name()+" Points: "+ achievement.getAchievement_Points());
        }
        return AchievementStrings;
    }


    public Achievement[] getUserAchievements() throws ParseException {
        Client client = ClientBuilder.newClient();


        WebTarget webTarget = client.target("http://localhost:8091/achievement/getuserachievements");

        Form form = new Form();
        LoadSerialized loadSerialized = new LoadSerialized();
        try {
            form.param("UserId", loadSerialized.LoadQuestion("/Users/ruben/Desktop/Big Idea/Chemistry-Buddy/src/main/java/serializer/user.ser"));
        } catch (IOException e) {
            log.log(Level.SEVERE, (Supplier<String>) e);
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

            Gson gson = new Gson();

            return gson.fromJson(String.valueOf(jsonArray), Achievement[].class);
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
        log.log(Level.INFO, achievementJson);

        response.close();
        return (JSONObject) parser.parse(achievementJson);
    }
}
