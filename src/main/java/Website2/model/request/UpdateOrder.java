package Website2.model.request;

import Website2.model.entity.OrderStatus;
import lombok.Data;

import java.util.Date;
@Data
public class UpdateOrder {
    private int total;
    private String address;
    private Date orderDate;
    private Date saleDate;
    private OrderStatus status;
    private String note;
}

