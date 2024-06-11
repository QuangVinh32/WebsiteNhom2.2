package Website2.backend.model.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

}
