package Website2.backend.service;

import Website2.backend.model.entity.Order;
import Website2.backend.model.entity.Product;
import Website2.backend.model.request.CreateOrder;

import Website2.backend.model.request.UpdateOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IOrderService {
    List<Order> getAllOrders();

    List<Order> getAllOrdersPage(int page, int pageSize);

    Optional<Order> getProductById(int id);

    void createOrder(CreateOrder createOrder);

    Product updateOrder(int orderID, UpdateOrder updateOrder);

    void deleteOrder(int id);

}
