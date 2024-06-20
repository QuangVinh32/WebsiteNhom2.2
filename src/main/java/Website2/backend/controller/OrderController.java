package Website2.backend.controller;

import Website2.backend.dto.CartDTO;
import Website2.backend.dto.OrderDTO;
import Website2.backend.model.entity.Cart;
import Website2.backend.model.entity.Order;
import Website2.backend.model.request.CreateCart;
import Website2.backend.model.request.CreateOrder;
import Website2.backend.model.request.UpdateCart;
import Website2.backend.model.request.UpdateOrder;
import Website2.backend.service.ICartService;
import Website2.backend.service.IOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/order")
@CrossOrigin("*")
@Validated
public class OrderController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IOrderService orderService;

    @GetMapping("/get-all-order")
    List<OrderDTO> findAllOrder() {
        List<Order> orders = orderService.getAllOrders();
        List<OrderDTO> orderDTOS = orders.stream()
                .map(order -> mapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());

        return orderDTOS;
    }
    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrder createOrder) throws Exception {
        orderService.createOrder(createOrder);
        return ResponseEntity.ok("Thêm đơn hàng thành công");
    }
    @PutMapping("/update-order/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Integer id,@RequestBody UpdateOrder updateOrder) throws Exception {
        orderService.updateOrder(id,updateOrder);
        return ResponseEntity.ok("Sửa đơn hàng thành công");
    }
    @DeleteMapping("/delete-order/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Integer id){
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Xóa đơn hàng thàng công");
    }
}
