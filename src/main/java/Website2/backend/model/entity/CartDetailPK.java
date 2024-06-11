package Website2.backend.model.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
