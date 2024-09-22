package Website2.model.DTO;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
@Data
public class UserForAdmin {
    private Integer userId;

    private String username;

    private String password;

    private String email;

    private String fullName;

    private String role;

    private String image;

    private String phone;
}
