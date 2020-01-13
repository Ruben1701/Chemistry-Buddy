package model.DTO;

public class LoginDTO {
    private final String username;
    private final String password;

    public LoginDTO(String Username, String Password){
        this.username = Username;
        this.password = Password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
