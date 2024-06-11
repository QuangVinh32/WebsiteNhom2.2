package Website2.backend.model.entity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Table(name = "reviews")
public class Reviews {

    @EmbeddedId
    private ReviewPK reviewPK;

    @Column(name = "content")
    private String content;

    @Column(name = "rate")
    private int rate;

    // Many-to-One mapping with User based on userId in ReviewPK
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", insertable = false, updatable = false)
    private Users user;

    // Many-to-One mapping with Product based on productId in ReviewPK
    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "productId", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;
}
