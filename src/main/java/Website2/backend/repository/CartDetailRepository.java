package Website2.backend.repository;

import Website2.backend.model.entity.CartDetail;
import Website2.backend.model.entity.CartDetailPK;
import Website2.backend.model.request.PkCartDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDetailRepository extends JpaRepository<CartDetail, CartDetailPK> {

}
