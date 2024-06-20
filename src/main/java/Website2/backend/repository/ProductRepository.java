package Website2.backend.repository;

import Website2.backend.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    boolean existsByProductName (String productName);

    List<Product> findByTypeId (int typeId);

}
