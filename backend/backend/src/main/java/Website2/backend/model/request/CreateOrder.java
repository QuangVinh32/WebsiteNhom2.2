package Website2.backend.model.request;

import Website2.backend.model.entity.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
public class CreateOrder {
    private int orderId;
    private int total;
    private String address;
    private Date orderDate;
    private Date saleDate;
    private OrderStatus status;
    private String note;
}
