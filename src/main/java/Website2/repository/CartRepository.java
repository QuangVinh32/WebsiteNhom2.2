package Website2.repository;

import Website2.model.entity.Cart;
import Website2.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    List<Cart> findAllByUsers(Users users);
    Optional<Cart> findByUsers(Users user);




}
