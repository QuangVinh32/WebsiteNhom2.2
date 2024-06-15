package Website2.backend.service;


import Website2.backend.model.entity.Users;
import Website2.backend.model.request.UserRequest;

import java.util.List;
import java.util.Optional;

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
