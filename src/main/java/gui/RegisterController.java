package gui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class RegisterController {


    public TextField EmailTbutxt;
    public TextField PasswordTxt;
    public Text AlreadyRegisterredLink;

    public void RegisterClicked(ActionEvent actionEvent) {
        register(EmailTbutxt.getText(), PasswordTxt.getText());
    }

    public void AlreadyRegisteredClicked(MouseEvent mouseEvent) throws IOException {

        Parent blah = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("LoginScreen.fxml")));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }

    public void register(String Email, String Password) {
        LogManager lgmngr = LogManager.getLogManager();
        Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);

        String query;
        ObjectMapper mapper = new ObjectMapper();
        query = String.format("http://localhost:8090/auth/register?email=%s&password=%s", Email, Password);
        log.log(Level.INFO, "[Query] : " + query);
        //System.out.println("[Query] : " + query);

        HttpPost httpPost = new HttpPost(query);

        // Perform the query

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpPost)) {
            log.log(Level.INFO, "[Status Line] : " + response.getStatusLine());
            //System.out.println("[Status Line] : " + response.getStatusLine());
            HttpEntity entity = response.getEntity();
            final String entityString = EntityUtils.toString(entity);
            log.log(Level.INFO, "[Entity] : " + entityString);
            //System.out.println("[Entity] : " + entityString);
            Gson gson = new Gson();
            Response jsonResponse = gson.fromJson(entityString,Response.class);
            String stringResult = jsonResponse.getResult();
            log.log(Level.INFO, "[Result] : " + stringResult);
            //System.out.println("[Result] : " + stringResult );

        } catch (IOException e) {
            log.log(Level.SEVERE, "IOException : " + e.toString());
            //System.out.println("IOException : " + e.toString());
        }


    }

    class Response {

        private String operation = "n/a";
        private String expression = "n/a";
        private String result = "n/a";

        public String getOperation() {
            return operation;
        }

        public void setOperation(String operation) {
            this.operation = operation;
        }

        public String getExpression() {
            return expression;
        }

        public void setExpression(String expression) {
            this.expression = expression;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }
}
