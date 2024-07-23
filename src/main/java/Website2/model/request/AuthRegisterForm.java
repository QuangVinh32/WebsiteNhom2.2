package Website2.model.request;

import lombok.Data;


@Data
public class AuthRegisterForm {
    private String username;

    private String password;

    private String phone;

    private String fullName;

    private String email;

}
