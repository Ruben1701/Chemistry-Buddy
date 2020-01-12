package model.DTO;

public class RegisterDTO {
    private String username;
    private String password;

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
