package Website2.model.entity;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
@Entity
@Data
@Table(name = "cart_detail")
public class CartDetail {
    @EmbeddedId
    private CartDetailPK cartDetailPK;

    @Column(name = "count")
    private int count;//3+1

    @JsonBackReference
    @ManyToOne
    @MapsId("cartId") // Đặt tên của trường ở đây
    @JoinColumn(name = "cartId", referencedColumnName = "cartId", insertable = false, updatable = false)
    private Cart cart;

    @JsonBackReference
    @ManyToOne
    @MapsId("productId") // Đặt tên của trường ở đây
    @JoinColumn(name = "productId", referencedColumnName = "productId", insertable = false, updatable = false)
    private Product product;
}
