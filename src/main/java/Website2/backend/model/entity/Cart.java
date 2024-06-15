package Website2.backend.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "cart")
public class Cart implements Serializable {
    @EmbeddedId
    private Cart.CartPK cartId;

    @Column(name = "total")
    private int total;

    @OneToOne
    @JoinColumn(name = "userId")
    private Users users;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartDetail> cartDetails;

    @Embeddable
    @Data
    @NoArgsConstructor
    public static class CartPK implements Serializable {

        @Column(name = "UserId")
        private Integer UserId;

        @Column(name = "ProductId")
        private Integer ProductId;

    }
}
