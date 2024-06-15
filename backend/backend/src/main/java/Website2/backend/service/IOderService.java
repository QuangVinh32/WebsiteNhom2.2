package Website2.backend.service;

import Website2.backend.model.entity.Order;
import Website2.backend.model.request.CreateOrder;
import Website2.backend.model.request.UpdateOrder;

import java.util.List;
import java.util.Optional;

public interface IOderService {
    List<Order> findAllOrder();
    Optional<Order> findByIdOrder(int id);
    void createOrder(CreateOrder createOrder) throws Exception;
    Order updateOrder(int id,UpdateOrder updateOrder) throws Exception;
    void deleteOrder(int id);




}
