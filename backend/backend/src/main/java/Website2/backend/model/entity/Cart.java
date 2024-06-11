package Website2.backend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @Column(name = "cart_id")
    private int cartId;
    @Column(name = "total")
    private int total;
    @Column(name = "cart_detail")
    private String cartDetail;

}
