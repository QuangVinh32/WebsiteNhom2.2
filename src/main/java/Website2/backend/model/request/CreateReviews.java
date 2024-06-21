package Website2.backend.model.request;


import Website2.backend.model.entity.Product;
import Website2.backend.model.entity.ReviewPK;
import Website2.backend.model.entity.Users;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class CreateReviews {

    private int userId;

    private int productId;

    private String content;

    private int rate;

}
