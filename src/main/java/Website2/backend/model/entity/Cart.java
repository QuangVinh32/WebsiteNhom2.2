package Website2.backend.model.entity;

<<<<<<< HEAD:src/main/java/Website2/backend/model/entity/Cart.java
=======
import jakarta.persistence.*;
import lombok.Data;
>>>>>>> 0a285c5cfca88a488c352faa5d63af54bf6abb57:backend/backend/src/main/java/Website2/backend/model/entity/Cart.java

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "cart")
<<<<<<< HEAD:src/main/java/Website2/backend/model/entity/Cart.java
public class Cart{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
=======
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartId")
>>>>>>> 0a285c5cfca88a488c352faa5d63af54bf6abb57:backend/backend/src/main/java/Website2/backend/model/entity/Cart.java
    private int cartId;

    @Column(name = "total")
    private int total;

    @OneToOne
    @JoinColumn(name = "userId")
    private Users users;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartDetail> cartDetails;


}
