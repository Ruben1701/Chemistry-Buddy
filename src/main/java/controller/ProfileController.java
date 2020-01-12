package controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import model.Achievement;
import model.Player;
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
import service.ProfileService;
import service.UserInterfaceService;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ProfileController implements Initializable {

    public ListView userAchievementsLV;
    public ListView allAxchievementsLV;

    UserInterfaceService userInterfaceService = new UserInterfaceService();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ProfileService profileService = new ProfileService();

        allAxchievementsLV.setItems(profileService.getAllAchievements());
        try {
            userAchievementsLV.setItems(profileService.getUserAchievements());
        } catch (ParseException e) {
            LogManager lgmngr = LogManager.getLogManager();
            Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);
            log.log(Level.SEVERE, (Supplier<String>) e);
        }
    }



    public void Exit(ActionEvent actionEvent) throws IOException {
        userInterfaceService.changeScreenActionEvent(actionEvent, "LoginScreen.fxml");

    }

    public void homeScreen(ActionEvent actionEvent) throws IOException {
        userInterfaceService.changeScreenActionEvent(actionEvent, "Periodiek.fxml");
    }


}
