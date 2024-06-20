package Website2.backend.dto;

import Website2.backend.model.entity.OrderStatus;
import lombok.Data;

import java.util.Date;

@Data
public class OrderDTO {
    private int total;

    private String address;

    private Date orderDate;

    private Date saleDate;

    private OrderStatus status;

    private String note;
}
