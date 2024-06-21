package Website2.backend.service.Class;
import Website2.backend.model.entity.OrderDetail;
import Website2.backend.model.request.CreateOrderDetail;
import Website2.backend.model.request.PkOrderDetail;
import Website2.backend.model.request.UpdateOrderDetail;
import Website2.backend.service.IOrderDetailService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderDetailService implements IOrderDetailService {
    @Override
    public List<OrderDetail> findAllOrderDetail() {
        return null;
    }

    @Override
    public OrderDetail findById(PkOrderDetail pkOrderDetail) {
        return null;
    }

    @Override
    public void createOrderDetail(CreateOrderDetail createOrderDetail) {

    }

    @Override
    public OrderDetail updateOrderDetail(UpdateOrderDetail updateOrderDetail) {
        return null;
    }

    @Override
    public void deleteOrderDetail(OrderDetail orderDetail) {

    }
}
