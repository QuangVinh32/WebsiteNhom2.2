package Website2.model.request;

import lombok.Data;

@Data
public class AuthUpdateForm {
    private String oldPassword;
    private String newPassword;
}
