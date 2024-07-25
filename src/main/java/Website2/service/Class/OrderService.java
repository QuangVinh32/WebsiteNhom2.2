package Website2.service.Class;
import Website2.model.entity.Order;
import Website2.model.request.CreateOrder;
import Website2.model.Filter.FilterOrder;
import Website2.model.request.UpdateOrder;
import Website2.repository.OrderRepository;
import Website2.service.IOrderService;
import Website2.speacification.OrderSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private ModelMapper mapper;
    @ Autowired
    private OrderRepository orderRepository;
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Page<Order> getAllOrdersPage(Pageable pageable, FilterOrder filterOrder) {
        Specification<Order> spec = OrderSpecification.buildSpec(filterOrder);
        return orderRepository.findAll(spec,pageable);
    }


    @Override
    public Optional<Order> getOrderById(int orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public void createOrder(CreateOrder createOrder) throws Exception {
        Optional<Order> existingOrder = orderRepository.findById(createOrder.getOrderId());
        if (existingOrder.isPresent()){
            throw new Exception(".....");
        }
        Order orderDb = mapper.map(createOrder, Order.class);
        orderRepository.save(orderDb);

    }

    @Override
    public Order updateOrder(int orderID, UpdateOrder updateOrder) throws Exception {
        Optional<Order> orderDb = getOrderById(orderID);
        if (orderDb.isPresent()){
            Order existingOrder = orderDb.get();
            mapper.map(updateOrder, existingOrder);
            return orderRepository.save(existingOrder);
        }else {
            throw new Exception("Order không tìm thấy id");
        }
    }

    @Override
    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }
}
