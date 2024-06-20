package Website2.backend.repository;


import Website2.backend.model.entity.OrderDetail;
import Website2.backend.model.entity.OrderDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailPK> {


}
