package Website2.backend.model.entity;

import javax.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class CartDetailPK implements Serializable {
    @ManyToOne
    @JoinColumn(name = "cartId")
    private Cart cartId;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product productId;
}
