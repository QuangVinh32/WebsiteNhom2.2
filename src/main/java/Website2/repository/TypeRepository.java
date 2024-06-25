package Website2.repository;

import Website2.model.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TypeRepository extends JpaRepository<Type,Integer> {

}
