package Website2.repository;

import Website2.model.entity.OrderDetail;
import Website2.model.entity.OrderDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailPK> {

}
