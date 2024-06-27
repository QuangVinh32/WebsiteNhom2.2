package Website2.controller;

import Website2.model.DTO.CartDetailDTO;
import Website2.model.DTO.ReviewsDTO;
import Website2.model.entity.CartDetail;
import Website2.model.entity.Reviews;
import Website2.model.request.*;
import Website2.service.ICartDetailService;
import Website2.service.IReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/reviews")
@CrossOrigin("*")
@Validated
public class ReviewsController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IReviewService reviewService;

    @GetMapping("/find-all-reviews")
    public List<ReviewsDTO> findAllReview(){
        List<Reviews> reviews = reviewService.findAllReviews();
        List<ReviewsDTO> reviewsDTOS = reviews.stream()
                .map(reviews1 -> mapper.map(reviews1, ReviewsDTO.class))
                .collect(Collectors.toList());
        return reviewsDTOS;
    }
    @GetMapping("/find-reviews-by-id")
    public ReviewsDTO findReviewById(@RequestBody PkReviews request) {
        Reviews reviews = reviewService.findById(request);
        return mapper.map(reviews , ReviewsDTO.class);
    }
    @PostMapping("/create-reviews")
    public ResponseEntity<?> createReview(@Valid @RequestBody CreateReviews request){
        reviewService.createReviews(request);
        return ResponseEntity.ok("Thêm đánh giá thành công");
    }
    @PutMapping ("/update-reviews")
    public ResponseEntity<?> updateReview(@Valid @RequestBody UpdateReviews request){
        reviewService.updateReviews(request);
        return ResponseEntity.ok("Sửa đánh giá thành công");
    }
    @DeleteMapping("/delete-reviews")
    public ResponseEntity<?> deleteReview(@RequestBody PkReviews request){
        reviewService.deleteReviews(request);
        return ResponseEntity.ok("Xóa đánh giá thành công");
    }
}
