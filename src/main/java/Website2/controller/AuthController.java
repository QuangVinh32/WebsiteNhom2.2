package Website2.controller;

import Website2.model.request.AuthRegisterForm;
import Website2.model.request.AuthUpdateForm;
import Website2.service.IAuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final IAuthService service;

    @PostMapping("/register")
    public void create(@RequestBody AuthRegisterForm form) {
        service.create(form);
    }

    @PutMapping("/update")
    public void update(Principal principal, @RequestBody AuthUpdateForm form) {
        String username = principal.getName();
        service.update(username, form);
    }

}
