package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Achievement;
import org.json.simple.parser.ParseException;
import service.ProfileService;
import service.UserInterfaceService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ProfileController implements Initializable {

    public ListView userAchievementsLV;
    public ListView allAxchievementsLV;

    private final UserInterfaceService userInterfaceService = new UserInterfaceService();
    LogManager lgmngr = LogManager.getLogManager();
    Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ProfileService profileService = new ProfileService();

        try {
            ObservableList<String> allAchievements = FXCollections.observableList(profileService.toListConverter(true));
            ObservableList<String> userAchievements = FXCollections.observableList(profileService.toListConverter(false));

            allAxchievementsLV.setItems(allAchievements);
            userAchievementsLV.setItems(userAchievements);

        } catch (ParseException e) {
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
