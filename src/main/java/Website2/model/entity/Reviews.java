package Website2.model.entity;

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
    @MapsId("userId")
    @JoinColumn(name = "userId", referencedColumnName = "userId", insertable = false, updatable = false)
    private Users user;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "productId", referencedColumnName = "productId", insertable = false, updatable = false)
    private Product product;
}
