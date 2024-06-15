package Website2.backend.model.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
