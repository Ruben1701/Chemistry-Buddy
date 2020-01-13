package model.DTO;

public class RegisterDTO {
    private final String username;
    private final String password;

    public RegisterDTO(String Username, String Password){
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
