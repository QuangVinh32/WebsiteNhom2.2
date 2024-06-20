package Website2.backend.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Table(name = "`order_detail`")
public class OrderDetail {

    @EmbeddedId
    private OrderDetailPK orderDetailPK;

    @Column(name = "count")
    private int count;

    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "productId", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)

    private Product product;

    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "orderId", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Order order;
}
