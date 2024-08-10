package Website2.service.Class;
import Website2.model.DTO.OrderDTO;
import Website2.model.entity.Nsx;
import Website2.model.entity.Order;
import Website2.model.entity.OrderDetail;
import Website2.model.entity.Product;
import Website2.model.request.CreateOrder;
import Website2.model.request.FilterOrder;
import Website2.model.request.UpdateOrder;
import Website2.repository.OrderDetailRepository;
import Website2.repository.OrderRepository;
import Website2.repository.ProductRepository;
import Website2.service.IOrderService;
import Website2.speacification.OrderSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
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
    public OrderDTO getOrderById(int id) {
        // lay ra order theo ID
        Order order = orderRepository.findById(id).get();
        // lay ra ds order_detail theo order_id o tren
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderOrderId(id);
        return new OrderDTO(order, orderDetails);
    }

    @Override
    public void createOrder(CreateOrder createOrder) throws Exception {
       Order orderDb = mapper.map(createOrder, Order.class);
        orderRepository.save(orderDb);// luu order
        // xoa di so san da dc mua
        Map<Integer, Integer> mapAmountByProId = createOrder.getProductRequests()
                .stream().collect(Collectors.toMap(i->i.getIdPro(), i-> i.getAmount()));

        List<Integer> ids = createOrder.getProductRequests().stream().map(i->i.getIdPro()).collect(Collectors.toList());
        List<Product> products = productRepository.findAllByProductIdIn(ids);
        products.forEach(i-> {
            // tru so luong da mua
            i.setSoLuongTonKho(i.getSoLuongTonKho()-mapAmountByProId.get(i.getProductId()));
        });
        productRepository.saveAll(products);
    }

    @Override
    public Order updateOrder(int orderID, UpdateOrder updateOrder) throws Exception {
        // Tìm đơn hàng theo ID
        Optional<Order> optionalOrder = orderRepository.findById(orderID);
        if (!optionalOrder.isPresent()) {
            throw new Exception("Order not found");
        }

        // Lấy đối tượng đơn hàng hiện tại
        Order order = optionalOrder.get();

        // Cập nhật các trường với dữ liệu từ UpdateOrder
        order.setTotal(updateOrder.getTotal());
        order.setFullName(updateOrder.getFullName());
        order.setAddress(updateOrder.getAddress());
        order.setPhone(updateOrder.getPhone());
        order.setOrderDate(updateOrder.getOrderDate());
        order.setSaleDate(updateOrder.getSaleDate());
        order.setStatus(updateOrder.getStatus());
        order.setNote(updateOrder.getNote());

        // Lưu đơn hàng đã cập nhật vào cơ sở dữ liệu
        return orderRepository.save(order);
    }



    @Override
    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }




}
