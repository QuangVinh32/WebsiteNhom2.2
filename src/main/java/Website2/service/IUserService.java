package Website2.service;


import Website2.model.entity.Users;
import Website2.model.request.UserRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IUserService {

    List<Users> getAllUser();

    List<Users> searchUsersByUsername(String username);

    Optional<Users> findByUsername(String username);

    Optional<Users> getUserById(Integer userId);

    void CreateUser(UserRequest userRequest);

    Users updateUser(Integer userId, UserRequest userRequest);

    boolean deleteUser(Integer userId);

//    Page<Users> search(SearchUsers searchUsers);
}
