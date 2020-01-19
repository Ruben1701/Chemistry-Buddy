import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(Main.class.getClass().getResource("LoginScreen.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/LoginScreen.fxml"));
        Scene scene = new Scene(root, 1920, 1080);
        primaryStage.setTitle("Chemistry Buddy");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
