package Website2.backend.model.entity;

import lombok.Data;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", insertable = false, updatable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "productId", insertable = false, updatable = false)
    private Product product;
}
