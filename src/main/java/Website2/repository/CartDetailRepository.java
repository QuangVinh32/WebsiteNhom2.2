package Website2.repository;
import Website2.model.entity.Cart;
import Website2.model.entity.CartDetail;
import Website2.model.entity.CartDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, CartDetailPK> {
        List<CartDetail> findAllByCart(Cart cart);
        List<CartDetail> findByCart(Cart cart);


}
