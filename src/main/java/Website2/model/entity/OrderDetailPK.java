package Website2.model.entity;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
@Data
@Embeddable
public class OrderDetailPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product productId;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order orderId;


    // Constructor với tham số
    public OrderDetailPK(Order orderId, Product productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    // Constructor mặc định (không tham số)
    public OrderDetailPK() {
    }
}
