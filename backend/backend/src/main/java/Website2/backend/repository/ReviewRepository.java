package Website2.backend.repository;


import Website2.backend.model.entity.ReviewPK;
import Website2.backend.model.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Reviews, ReviewPK> {
}
