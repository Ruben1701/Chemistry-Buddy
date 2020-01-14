import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginScreen.fxml"));
        //FXMLLoader loader = new FXMLLoader(new File("/Users/ruben/Desktop/Big Idea/Chemistry-Buddy/src/main/resources/LoginScreen.fxml").toURI().toURL());
        Parent root = FXMLLoader.load(getClass().getResource("/LoginScreen.fxml"));
        //Parent root = loader.load();
        primaryStage.setTitle("Chemistry Buddy");
        primaryStage.setScene(new Scene(root, 1920, 1080));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
