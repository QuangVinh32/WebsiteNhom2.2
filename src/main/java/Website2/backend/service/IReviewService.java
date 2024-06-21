package Website2.backend.service;
import Website2.backend.model.entity.Reviews;
import Website2.backend.model.request.CreateReviews;
import Website2.backend.model.request.PkReviews;
import Website2.backend.model.request.UpdateReviews;
import java.util.List;

public interface IReviewService {
    List<Reviews> findAllReviews();
    Reviews findById(PkReviews pkReviews);
    void createReviews(CreateReviews createReviews);
    Reviews updateReviews(UpdateReviews updateReviews);
    void deleteReviews(PkReviews pkReviews);

}
