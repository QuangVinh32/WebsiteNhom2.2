package Website2.backend.service;

import Website2.backend.model.entity.Product;
import Website2.backend.model.request.CreateProduct;
import Website2.backend.model.request.ProductRequest;
import Website2.backend.model.request.UpdateProduct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
