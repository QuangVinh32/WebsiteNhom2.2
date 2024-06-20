package Website2.backend.repository;

import Website2.backend.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {

    List<Cart> fingByUserId(Integer userId);

}
