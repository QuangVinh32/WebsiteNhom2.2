package Website2.backend.model.entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    private int id;
    @Column(name = "product_id")
    private int productId;
    @Column(name = "order_id")
    private int orderId;
    @Column(name = "count")
    private int count;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
