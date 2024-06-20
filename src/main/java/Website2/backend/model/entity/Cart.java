package Website2.backend.model.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "cart")
public class Cart{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;

    @Column(name = "total")
    private int total;

    @OneToOne
    @JoinColumn(name = "userId")
    private Users users;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartDetail> cartDetails;


}
