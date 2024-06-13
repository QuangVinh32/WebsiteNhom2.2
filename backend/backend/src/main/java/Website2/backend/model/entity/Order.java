package Website2.backend.model.entity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "`order`")
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
    @Column(name = "orderDate")
    private Date orderDate;
    @Column(name = "saleDate")
    private Date saleDate;
    @Column(name = "status")
    private OrderStatus status;
    @Column(name = "note")
    private String note;

    // Đúng
    @ManyToOne
    @JoinColumn(name = "userId")
    private Users users;

    // Đúng
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;
}
