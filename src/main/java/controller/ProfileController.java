package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
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
