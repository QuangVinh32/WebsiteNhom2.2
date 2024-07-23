package Website2.service;

import Website2.model.request.AuthRegisterForm;
import Website2.model.request.AuthUpdateForm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IAuthService extends UserDetailsService {
    void create(AuthRegisterForm form);

    void update(String username, AuthUpdateForm form);

}
