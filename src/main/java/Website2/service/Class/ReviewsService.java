package Website2.service.Class;
import Website2.model.entity.*;
import Website2.model.request.CreateReviews;
import Website2.model.request.PkReviews;
import Website2.model.request.UpdateReviews;
import Website2.repository.ProductRepository;
import Website2.repository.ReviewRepository;
import Website2.repository.UserRepository;
import Website2.service.IReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
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

//    @Override
//    public List<Reviews> findByProductId(int productId) {
//        return reviewRepository.findByProductId(productId);
//    }

    @Override
    public Reviews findById(PkReviews pkReviews) {
        ReviewPK reviewPK = pkReviews.getReviewPK();
        Reviews reviews = reviewRepository.findById(reviewPK)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy id mong muốn"));
        return reviews;
    }

    @Override
    public void createReviews(CreateReviews createReviews) {
        Reviews reviews = new Reviews();
        ReviewPK reviewPK = new ReviewPK();

        // Copy properties from createReviews to reviews
        BeanUtils.copyProperties(createReviews, reviews);

        reviews.setReviewPK(reviewPK);

        // Retrieve the user and product, throwing an exception if not found
        Users user = userRepository.findById(createReviews.getUserId())
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        Product product = productRepository.findById(createReviews.getProductId())
                .orElseThrow(() -> new NoSuchElementException("Product not found"));

        // Set the reviewPK with the found user and product
        reviewPK.setUserId(user);
        reviewPK.setProductId(product);
        reviews.setReviewPK(reviewPK);

        // Set the user and product for the review
        reviews.setUser(user);
        reviews.setProduct(product);

        // Set rate and content
        reviews.setRate(createReviews.getRate());
        reviews.setContent(createReviews.getContent());
        reviews.setCreateTimeReview(createReviews.getTime() != null ? createReviews.getTime() : LocalDateTime.now());

        // Save the review
        reviewRepository.save(reviews);
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
