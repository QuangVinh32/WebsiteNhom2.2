package Website2.backend.service;

import Website2.backend.model.entity.Product;
import Website2.backend.model.request.CreateProduct;
import Website2.backend.model.request.UpdateProduct;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> getAllProducts();
    List<Product> getAllProductspage(int page, int pageSize);
    Optional<Product> getProductById(int id);
    void creatProduct(CreateProduct createProduct) throws Exception;
    Product updateProduct(int productId, UpdateProduct updateProduct);
    void deleteProduct(int id);

}
