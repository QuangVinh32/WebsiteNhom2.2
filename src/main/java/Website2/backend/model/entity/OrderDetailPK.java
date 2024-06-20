package Website2.backend.model.entity;

<<<<<<< HEAD:src/main/java/Website2/backend/model/entity/OrderDetailPK.java
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

=======
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

@Embeddable
public class OrderDetailPK implements Serializable {
    // Đúng
    @ManyToOne
    @JoinColumn(name = "productId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    // Đúng
    @ManyToOne
    @JoinColumn(name = "orderId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Order orderId;
>>>>>>> 0a285c5cfca88a488c352faa5d63af54bf6abb57:backend/backend/src/main/java/Website2/backend/model/entity/OrderDetailPK.java
}
