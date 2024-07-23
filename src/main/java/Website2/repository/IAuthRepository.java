package Website2.repository;

import Website2.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthRepository extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);
}
