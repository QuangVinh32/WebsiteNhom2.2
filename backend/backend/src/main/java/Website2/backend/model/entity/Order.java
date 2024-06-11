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
    @Column(name = "order_id")
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
    private orderStatus status;
    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "userId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Users users;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;
}
