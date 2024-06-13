package Website2.backend.repository;

import Website2.backend.model.entity.Order;
import Website2.backend.model.entity.OrderDetail;
import Website2.backend.model.entity.OrderDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailPK> {
}
