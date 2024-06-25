package Website2.model.entity;

import javax.persistence.*;
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
