package Website2.service.Class;
import Website2.model.entity.CartDetail;
import Website2.model.entity.CartDetailPK;
import Website2.model.entity.ReviewPK;
import Website2.model.entity.Reviews;
import Website2.model.request.CreateReviews;
import Website2.model.request.PkReviews;
import Website2.model.request.UpdateReviews;
import Website2.repository.ProductRepository;
import Website2.repository.ReviewRepository;
import Website2.repository.UserRepository;
import Website2.service.IReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public class ReviewsService implements IReviewService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public List<Reviews> findAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Reviews findById(PkReviews pkReviews) {
        ReviewPK reviewPK = pkReviews.getReviewPK();
        Reviews reviews = reviewRepository.findById(reviewPK)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy id mong muốn"));
        return reviews;
    }

    @Override
    public void createReviews(CreateReviews createReviews) {
    }
    @Override
    public Reviews updateReviews(UpdateReviews updateReviews) {
        return null;
    }

    @Override
    public void deleteReviews(PkReviews pkReviews) {
        ReviewPK reviewPK = pkReviews.getReviewPK();
        Reviews reviews = reviewRepository.findById(reviewPK)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy id mong muốn"));
        reviewRepository.delete(reviews);
    }
}
