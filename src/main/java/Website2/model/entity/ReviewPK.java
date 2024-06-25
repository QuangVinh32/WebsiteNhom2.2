

import javax.persistence.*;

import Website2.model.entity.Product;
import Website2.model.entity.Users;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class ReviewPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "userId")
    private Users userId;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product productId;
}
