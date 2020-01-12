package model.DTO;

public class LoginDTO {
    private String username;
    private String password;

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
