package Website2.backend.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Data
@Table(name = "`cart_detail`")
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "count")
    private int count;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cart cart;

    @OneToMany(mappedBy = "cartDetail", cascade = CascadeType.ALL)
    private List<Product> products;  // Adjust mappedBy to match the field in Product
}
