package Website2.service.Class;
import Website2.model.entity.OrderDetail;
import Website2.model.request.CreateOrderDetail;
import Website2.model.request.PkOrderDetail;
import Website2.model.request.UpdateOrderDetail;
import Website2.service.IOrderDetailService;
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
