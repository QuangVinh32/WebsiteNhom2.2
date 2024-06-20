package Website2.backend.service.Class;
import Website2.backend.model.entity.Order;
import Website2.backend.model.entity.Product;
import Website2.backend.model.request.CreateOrder;
import Website2.backend.model.request.UpdateOrder;
import Website2.backend.repository.OrderRepository;
import Website2.backend.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getAllOrdersPage(int page, int pageSize) {
        return null;
    }

    @Override
    public Optional<Order> getProductById(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public void createOrder(CreateOrder createOrder) {

    }

    @Override
    public Product updateOrder(int orderID, UpdateOrder updateOrder) {
        return null;
    }

    @Override
    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }
}
