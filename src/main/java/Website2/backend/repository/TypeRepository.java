package Website2.backend.repository;

import Website2.backend.model.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TypeRepository extends JpaRepository<Type,Integer> {

}


