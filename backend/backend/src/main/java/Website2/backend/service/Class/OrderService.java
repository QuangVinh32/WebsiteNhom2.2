package Website2.backend.service.Class;

import Website2.backend.model.entity.Order;
import Website2.backend.model.request.CreateOrder;
import Website2.backend.model.request.UpdateOrder;
import Website2.backend.repository.OrderRepository;
import Website2.backend.service.IOderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOderService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private OrderRepository orderRepository;


    @Override
    public List<Order> findAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findByIdOrder(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public void createOrder(CreateOrder createOrder) throws Exception {
        Optional<Order> existingKhachHang = orderRepository.findById(createOrder.getOrderId());
        if (existingKhachHang.isPresent()) {
            throw new Exception("Order đã tồn tại");
        }
        Order orderDb = mapper.map(createOrder, Order.class);
        orderRepository.save(orderDb);
    }

    @Override
        public Order updateOrder(int id, UpdateOrder updateOrder) throws Exception {
        Optional<Order> orderDb = findByIdOrder(id);
        if (orderDb.isPresent()) {
            Order existingOrder = orderDb.get();
            // Cách 1
//            existingOrder.setTotal(updateOrder.getTotal());
//            existingOrder.setAddress(updateOrder.getAddress());
//            existingOrder.setOrderDate(updateOrder.getOrderDate());
//            existingOrder.setSaleDate(updateOrder.getSaleDate());
//            existingOrder.setStatus(updateOrder.getStatus());
//            existingOrder.setNote(updateOrder.getNote());
            // Cách 2
            mapper.map(updateOrder, existingOrder);


            // Lưu đối tượng Order đã được cập nhật vào cơ sở dữ liệu
            return orderRepository.save(existingOrder);
        } else {
            // Xử lý trường hợp không tìm thấy đơn hàng với ID đã cho
            throw new Exception("Order không tìm thấy id: " + id);
        }
    }
    @Override
    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }
}
