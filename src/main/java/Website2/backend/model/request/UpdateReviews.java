package Website2.backend.model.request;
import Website2.backend.model.entity.ReviewPK;
import lombok.Data;

@Data
public class UpdateReviews {
    private ReviewPK reviewPK;
    private String content;
    private int rate;

}
