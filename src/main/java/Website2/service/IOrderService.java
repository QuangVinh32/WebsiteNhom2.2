package Website2.service;

import Website2.model.entity.Order;
import Website2.model.entity.Product;
import Website2.model.request.CreateOrder;

import Website2.model.request.UpdateOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IOrderService {
    List<Order> getAllOrders();

    List<Order> getAllOrdersPage(int page, int pageSize);

    Optional<Order> getProductById(int id);

    void createOrder(CreateOrder createOrder) throws Exception;

    Order updateOrder(int orderID, UpdateOrder updateOrder) throws Exception;

    void deleteOrder(int id);

}
