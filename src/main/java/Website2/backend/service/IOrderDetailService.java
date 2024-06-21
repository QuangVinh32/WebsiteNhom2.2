package Website2.backend.service;

import Website2.backend.model.entity.CartDetail;
import Website2.backend.model.entity.OrderDetail;
import Website2.backend.model.request.*;

import java.util.List;

public interface IOrderDetailService {
    List<OrderDetail> findAllOrderDetail();
    OrderDetail findById(PkOrderDetail pkOrderDetail);
    void createOrderDetail(CreateOrderDetail createOrderDetail);
    OrderDetail updateOrderDetail(UpdateOrderDetail updateOrderDetail);
    void deleteOrderDetail(OrderDetail orderDetail);
}
