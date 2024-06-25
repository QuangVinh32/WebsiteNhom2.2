package Website2.repository;
import Website2.model.entity.ReviewPK;
import Website2.model.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Reviews, ReviewPK> {
}
