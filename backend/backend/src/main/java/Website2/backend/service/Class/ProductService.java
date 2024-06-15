package Website2.backend.service.Class;

import Website2.backend.model.entity.Product;
import Website2.backend.model.request.CreateProduct;
import Website2.backend.model.request.ProductRequest;
import Website2.backend.repository.ProductRepository;
import Website2.backend.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll() ;
    }

    @Override
    public List<Product> getAllProductspage(int page, int pageSize) {
        return null;
    }

    @Override
    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public void creatProduct(CreateProduct createProduct) {

    }

    @Override
    public Product updateProduct(int productId, ProductRequest productRequest) {
        return null;
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}
