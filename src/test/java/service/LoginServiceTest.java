package service;

import model.DTO.LoginDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

    @Test
    void correctlogin() {
        LoginService loginService = new LoginService();
        String Username = "Test";
        String Password = "Test";
        LoginDTO loginDTO = new LoginDTO(Username, Password);
        assertEquals(loginService.login(loginDTO), "8");
    }

    @Test
    void incorrectlogin() {
        LoginService loginService = new LoginService();
        String Username = "Test";
        String Password = "Test";
        LoginDTO loginDTO = new LoginDTO(Username, "blah");
        assertEquals(loginService.login(loginDTO), "8");
    }
}