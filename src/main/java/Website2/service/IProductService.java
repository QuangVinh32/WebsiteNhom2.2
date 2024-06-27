package Website2.service;

import Website2.model.entity.Product;
import Website2.model.request.CreateProduct;
import Website2.model.request.UpdateProduct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface IProductService {
    List<Product> getAllProducts();

    List<Product> getAllProductsPage(int page, int pageSize);

    Optional<Product> getProductById(int id);

    void createProduct(CreateProduct createProduct) throws Exception;

    Product updateProduct(int productId, UpdateProduct updateProduct) throws Exception;

    void deleteProduct(int id);

    boolean isProductNameExists(String productName);

    List<Product> getAllProductByTypeId(Integer typeId);
}
