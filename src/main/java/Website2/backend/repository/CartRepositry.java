package Website2.backend.repository;

import Website2.backend.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepositry extends JpaRepository<Cart,Integer> {
}
