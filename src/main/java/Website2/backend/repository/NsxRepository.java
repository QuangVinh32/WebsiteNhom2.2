package Website2.backend.repository;

import Website2.backend.model.entity.Nsx;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface NsxRepository extends JpaRepository<Nsx,Integer> {


}
