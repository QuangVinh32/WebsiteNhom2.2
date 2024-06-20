package Website2.backend.controller;

import Website2.backend.model.entity.Users;
import Website2.backend.model.request.UserRequest;
import Website2.backend.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/v1/user")
@Validated
public class UserController {
    @Autowired
    IUserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserRequest request) {
        userService.CreateUser(request);
        return ResponseEntity.ok("Thêm Tài khoản thành công");
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody UserRequest request) {
        userService.updateUser(id, request);
        return ResponseEntity.ok("Sửa Tài khoản thành công");
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Xóa Tài khoản thành công");
    }

    @GetMapping("/find-By-Id/{id}")
    public Optional<Users> findUserById(@PathVariable  String username) {
        return userService.findByUsername(username);
    }

    @GetMapping("/find-all")
    public Page<Users> findAllUser() {
        return (Page<Users>) userService.getAllUser();
    }
}
