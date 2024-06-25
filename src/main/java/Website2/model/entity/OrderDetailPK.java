package Website2.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
@Data
@Embeddable
public class OrderDetailPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;

}
