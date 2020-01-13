package service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import interfaces.iRegister;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import model.DTO.RegisterDTO;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class RegisterService implements iRegister {
    @Override
    public void register(RegisterDTO registerDTO) {
        LogManager lgmngr = LogManager.getLogManager();
        Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);

        String query;
        ObjectMapper mapper = new ObjectMapper();
        query = String.format("http://localhost:8090/auth/register?email=%s&password=%s", registerDTO.getUsername(), registerDTO.getPassword());
        log.log(Level.INFO, "[Query] : " + query);

        HttpPost httpPost = new HttpPost(query);

        // Perform the query

//        try (CloseableHttpClient httpClient = HttpClients.createDefault();
//             CloseableHttpResponse response = httpClient.execute(httpPost)) {
//            log.log(Level.INFO, "[Status Line] : " + response.getStatusLine());
//            HttpEntity entity = response.getEntity();
//            final String entityString = EntityUtils.toString(entity);
//            log.log(Level.INFO, "[Entity] : " + entityString);
//            Gson gson = new Gson();
//            RegisterService.Response jsonResponse = gson.fromJson(entityString, RegisterService.Response.class);
//            String stringResult = jsonResponse.getResult();
//            log.log(Level.INFO, "[Result] : " + stringResult);
//
//        } catch (IOException e) {
//            log.log(Level.SEVERE, "IOException : " + e.toString());
//        }

    }

}
