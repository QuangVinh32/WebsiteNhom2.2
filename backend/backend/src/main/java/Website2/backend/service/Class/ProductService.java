package Website2.backend.service.Class;

import Website2.backend.model.entity.Order;
import Website2.backend.model.entity.Product;
import Website2.backend.model.request.CreateProduct;
import Website2.backend.model.request.UpdateProduct;
import Website2.backend.repository.ProductRepository;
import Website2.backend.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ModelMapper mapper;
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
    public void creatProduct(CreateProduct createProduct) throws Exception {
        Optional<Product> existingProduct = productRepository.findById(createProduct.getProductId());
        if (existingProduct.isPresent()) {
            throw new Exception("Product đã tồn tại");
        }
        Product productDb = mapper.map(createProduct, Product.class);
        productRepository.save(productDb);

    }

    @Override
    public Product updateProduct(int productId, UpdateProduct updateProduct) {
        return null;
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}
