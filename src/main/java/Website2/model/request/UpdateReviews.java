package Website2.model.request;
import Website2.model.entity.ReviewPK;
import lombok.Data;

@Data
public class UpdateReviews {
    private ReviewPK reviewPK;
    private String content;
    private int rate;

}
