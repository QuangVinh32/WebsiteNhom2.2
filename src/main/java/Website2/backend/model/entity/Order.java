package Website2.backend.model.entity;

import javax.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "Orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private int orderId;

    @Column(name = "total")
    private int total;

    @Column(name = "address")
    private String address;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "sale_date")
    private Date saleDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING) // Sử dụng EnumType.STRING để lưu trữ giá trị của Enum dưới dạng chuỗi
    private OrderStatus status;

    @Column(name = "note")
    private String note;
}
