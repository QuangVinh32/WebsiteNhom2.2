package Website2.service;

import Website2.model.DTO.ProductForAdmin;
import Website2.model.DTO.ProductForUser;
import Website2.model.entity.Product;
import Website2.model.request.CreateProduct;
import Website2.model.request.FilterProduct;
import Website2.model.request.UpdateProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductService {

    Page<Product> getAllProductsPage(Pageable pageable, FilterProduct filterProduct);

    ProductForUser getProductById(int id);
    ProductForAdmin getProductByIdOld(int id);

    void createProduct(CreateProduct createProduct) throws Exception;

    Product updateProduct(int productId, UpdateProduct updateProduct) throws Exception;

    void deleteProduct(int id);

    boolean isProductNameExists(String productName);

    List<Product> getAllProductByTypeId(Integer typeId);
}
