package Website2.service;


import Website2.model.entity.Users;
import Website2.model.request.FilterUser;
import Website2.model.request.UserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IUserService {

    List<Users> getAllUser();

     Page<Users> getAllUsersPage(Pageable pageable, FilterUser filterUser);

    List<Users> searchUsersByUsername(String username);

    Optional<Users> findByUsername(String username);

    Optional<Users> getUserById(Integer userId);

    void CreateUser(UserRequest userRequest);

    Users updateUser(Integer userId, UserRequest userRequest);

    boolean deleteUser(Integer userId);

}
