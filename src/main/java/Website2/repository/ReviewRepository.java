package Website2.repository;
import Website2.model.entity.ReviewPK;
import Website2.model.entity.Reviews;
import Website2.model.request.PkReviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Reviews, ReviewPK> {
//    List<Reviews> findByProductId(int productId);

}
