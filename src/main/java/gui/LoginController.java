package gui;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Objects;

public class LoginController {


    public TextField EmailTxt;
    public TextField PasswordTxt;

    public void LoginClicked(ActionEvent actionEvent) throws IOException {
        if (login(EmailTxt.getText(), PasswordTxt.getText())){
            Parent blah = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Periodiek.fxml")));
            Scene scene = new Scene(blah);
            Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
        }
    }


    public Boolean login(String Email, String Password) {

        String query;
        query = String.format("http://localhost:8090/auth/login?email=%s&password=%s", Email, Password);
        System.out.println("[Query] : " + query);

        HttpPost httpPost = new HttpPost(query);

        // Perform the query

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpPost)) {
            System.out.println("[Status Line] : " + response.getStatusLine());
            HttpEntity entity = response.getEntity();
            final String entityString = EntityUtils.toString(entity);
            System.out.println("[Entity] : " + entityString);
            Gson gson = new Gson();
            Response jsonResponse = gson.fromJson(entityString,Response.class);
            JsonObject jsonObject = new JsonParser().parse(entityString).getAsJsonObject();
            System.out.println(jsonObject.get("success").getAsString()); //John

            return Objects.equals(jsonObject.get("success").getAsString(), "true");

        } catch (IOException e) {
            System.out.println("IOException : " + e.toString());
        }
        return false;

    }

    public void NoAccountClicked(MouseEvent mouseEvent) throws IOException {
        Parent blah = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("RegisterScreen.fxml")));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }

    class Response {

        private String success = "n/a";


        public String getSuccess() {
            return success;
        }

        public void setSuccess(String success) {
            this.success = success;
        }

    }
}
