package Website2.service.Class;

import Website2.model.entity.Users;
import Website2.model.request.AuthRegisterForm;
import Website2.model.request.AuthUpdateForm;
import Website2.repository.IAuthRepository;
import Website2.service.IAuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {
    private final IAuthRepository repository;
    private final ModelMapper mapper;
    private final PasswordEncoder encoder;

    @Autowired
    public AuthService(IAuthRepository repository, ModelMapper mapper, PasswordEncoder encoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.encoder = encoder;
    }

    @Override
    public void create(AuthRegisterForm form) {
        Users user =mapper.map(form, Users.class);
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(Users.Role.USER);
        repository.save(user);
    }

    @Override
    public void update(String username, AuthUpdateForm form) {
        Users user = repository.findByUsername(username);
        if (encoder.matches(form.getOldPassword(), user.getPassword())) {
            String encodedPassword = encoder.encode(form.getNewPassword());
            user.setPassword(encodedPassword);
            repository.save(user);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = repository.findByUsername(username);
        if (users == null) {
            throw new UsernameNotFoundException(username);
        }
        return User.builder()
                .username(users.getUsername())
                .password(users.getPassword())
                .authorities(users.getRole().toString())
                .build();
    }
}
