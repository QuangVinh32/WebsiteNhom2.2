package Website2.backend.model.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

@Embeddable
public class OrderDetailPK implements Serializable {
    // Đúng
    @ManyToOne
    @JoinColumn(name = "productId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    // Đúng
    @ManyToOne
    @JoinColumn(name = "orderId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Order orderId;
}
