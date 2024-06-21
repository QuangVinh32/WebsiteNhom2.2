package Website2.backend.service.Class;
import Website2.backend.model.entity.Reviews;
import Website2.backend.model.request.CreateReviews;
import Website2.backend.model.request.PkReviews;
import Website2.backend.model.request.UpdateReviews;
import Website2.backend.repository.ProductRepository;
import Website2.backend.repository.ReviewRepository;
import Website2.backend.repository.UserRepository;
import Website2.backend.service.IReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

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
        return null;
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

    }
}
