package Website2.backend.model.entity;

<<<<<<< HEAD:src/main/java/Website2/backend/model/entity/CartDetailPK.java
import javax.persistence.*;
=======
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
>>>>>>> 0a285c5cfca88a488c352faa5d63af54bf6abb57:backend/backend/src/main/java/Website2/backend/model/entity/CartDetailPK.java
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
