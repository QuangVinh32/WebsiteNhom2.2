package Website2.service.Class;
import Website2.model.DTO.OrderDTO;
import Website2.model.entity.*;
import Website2.model.request.CreateOrder;
import Website2.model.request.FilterOrder;
import Website2.model.request.UpdateOrder;
import Website2.repository.*;
import Website2.service.IOrderService;
import Website2.speacification.OrderSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;
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
    private CartRepository cartRepository;
    @Autowired
    private CartDetailRepository cartDetailRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private UserRepository userRepository;
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


//    @Override
//    public void createOrder(CreateOrder createOrder) throws Exception {
//       Order orderDb = mapper.map(createOrder, Order.class);
//        orderRepository.save(orderDb);// luu order
//        // xoa di so san da dc mua
//        Map<Integer, Integer> mapAmountByProId = createOrder.getProductRequests()
//                .stream().collect(Collectors.toMap(i->i.getIdPro(), i-> i.getAmount()));
//
//        List<Integer> ids = createOrder.getProductRequests().stream().map(i->i.getIdPro()).collect(Collectors.toList());
//        List<Product> products = productRepository.findAllByProductIdIn(ids);
//        products.forEach(i-> {
//            // tru so luong da mua
//            i.setSoLuongTonKho(i.getSoLuongTonKho()-mapAmountByProId.get(i.getProductId()));
//        });
//        productRepository.saveAll(products);
//
//        // tao orderDetail(idsp , id don hang, so luong)// soluong = soluong o cartdetail bi xoa di
//        //+++ xoa di cartDetail (idsp , id gio hang, so luong)
//        // lay username nguoi dang nhap
//        // => lay gio hang theo username nguoi dang nhap
//        //createOrder.getProductRequests() -> ds cac sp can xoa ở cart detail
//        // ds sp can xoa va id gio hang => tim ra ds cart detail muon xoa
//        // List<CartDetail>  ls findByCartIdAndProductIdIn(id gio hang, list id sp)
//        //  select * from cartDetail where cart_id = ? and product_id in (1,2)
//        // delete ls
//    }


    @Transactional
    public void createOrder(CreateOrder createOrderDTO) {
        // Lấy thông tin người dùng hiện tại từ SecurityContext
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Kiểm tra xem người dùng đã có Cart hay chưa
        Cart cart = cartRepository.findByUsers(user).orElse(null);

        if (cart == null || cart.getCartDetails().isEmpty()) {
            throw new IllegalArgumentException("Giỏ hàng trống");
        }

        // Tạo đối tượng Order
        Order order = new Order();
        order.setFullName(createOrderDTO.getFullName());
        order.setAddress(createOrderDTO.getAddress());
        order.setPhone(createOrderDTO.getPhone());
        order.setTotal(cart.getTotal());
        order.setOrderDate(new Date());
        order.setStatus(OrderStatus.Ordered);  // Đặt trạng thái mặc định cho đơn hàng

        orderRepository.save(order);

        // Tạo chi tiết đơn hàng từ chi tiết giỏ hàng
        for (CartDetail cartDetail : cart.getCartDetails()) {
            OrderDetailPK orderDetailPK = new OrderDetailPK(order, cartDetail.getProduct());
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderDetailPK(orderDetailPK);
            orderDetail.setCount(cartDetail.getCount());

            orderDetailRepository.save(orderDetail);
        }

        // Xóa giỏ hàng sau khi tạo đơn hàng
        cartDetailRepository.deleteAll(cart.getCartDetails());
        cartRepository.delete(cart);
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
