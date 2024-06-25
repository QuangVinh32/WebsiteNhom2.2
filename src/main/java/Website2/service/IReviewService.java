package Website2.service;
import Website2.model.entity.Reviews;
import Website2.model.request.CreateReviews;
import Website2.model.request.PkReviews;
import Website2.model.request.UpdateReviews;
import java.util.List;

public interface IReviewService {
    List<Reviews> findAllReviews();
    Reviews findById(PkReviews pkReviews);
    void createReviews(CreateReviews createReviews);
    Reviews updateReviews(UpdateReviews updateReviews);
    void deleteReviews(PkReviews pkReviews);

}
