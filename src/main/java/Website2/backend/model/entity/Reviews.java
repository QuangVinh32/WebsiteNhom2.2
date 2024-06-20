package Website2.backend.model.entity;
<<<<<<< HEAD:src/main/java/Website2/backend/model/entity/Reviews.java
import javax.persistence.*;import lombok.Data;
=======
import jakarta.persistence.*;
import lombok.Data;
>>>>>>> 0a285c5cfca88a488c352faa5d63af54bf6abb57:backend/backend/src/main/java/Website2/backend/model/entity/Reviews.java
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
<<<<<<< HEAD:src/main/java/Website2/backend/model/entity/Reviews.java
=======
    @OnDelete(action = OnDeleteAction.CASCADE)
>>>>>>> 0a285c5cfca88a488c352faa5d63af54bf6abb57:backend/backend/src/main/java/Website2/backend/model/entity/Reviews.java
    private Product product;
}
