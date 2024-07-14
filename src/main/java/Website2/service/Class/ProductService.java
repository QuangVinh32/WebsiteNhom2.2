package Website2.service.Class;

import Website2.model.DTO.ProductDTOv2;

import Website2.model.entity.Product;
import Website2.model.entity.Reviews;
import Website2.model.request.CreateProduct;
import Website2.model.request.FilterProduct;
import Website2.model.request.UpdateProduct;
import Website2.repository.ProductRepository;
import Website2.service.IProductService;
import Website2.speacification.ProductSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Page<Product> getAllProductsPage(Pageable pageable,FilterProduct filterProduct) {
        Specification<Product> spec = ProductSpecification.buildSpec(filterProduct);
        return productRepository.findAll(spec,pageable);
    }
    @Override
    public ProductDTOv2 getProductById(int id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(this::convertToDto).orElse(null);
    }


    private ProductDTOv2 convertToDto(Product product){
        ProductDTOv2 productDTO = new ProductDTOv2();
        productDTO.setProductId(product.getProductId());
        productDTO.setProductCode(product.getProductCode());
        productDTO.setProductName(product.getProductName());
        productDTO.setDescriptionProduct(product.getProductDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setDiscount(product.getDiscount());
        productDTO.setImage(product.getImage());
        productDTO.setStatus(product.getStatus());
        productDTO.setCreatedTime(product.getCreateTime());
        productDTO.setSoLuongTonKho(product.getSoLuongTonKho());

        List<ProductDTOv2.ReviewsDTO> reviewsDTOS = product.getReviews().stream()
                        .map(this::reviewsDTO)
                .collect(Collectors.toList());
        productDTO.setReviews(reviewsDTOS);
        return productDTO;
    }

    private ProductDTOv2.ReviewsDTO reviewsDTO(Reviews reviews){
        ProductDTOv2.ReviewsDTO reviewsDTO = new ProductDTOv2.ReviewsDTO();
        reviewsDTO.setContent(reviews.getContent());
        reviewsDTO.setRate(reviews.getRate());
        if (reviews.getUser() !=null){
            reviewsDTO.setUsers(ProductDTOv2.ReviewsDTO.UsersDTO.convertToDto(reviews.getUser()));

        }
        return reviewsDTO;
    }


    @Override
    public void createProduct(CreateProduct createProduct) throws Exception {
        Product productDb = mapper.map(createProduct, Product.class);
        productRepository.save(productDb);

    }


    @Override
    public Product updateProduct(int productId, UpdateProduct updateProduct) throws Exception {
        ProductDTOv2 productDb = getProductById(productId);
        if (productDb != null) {
            Product existingProduct = productRepository.findById(productId)
                    .orElseThrow(() -> new Exception("Product không tìm thấy id"));
            mapper.map(updateProduct, existingProduct);
            return productRepository.save(existingProduct);
        } else {
            throw new Exception("Product không tìm thấy id");
        }
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public boolean isProductNameExists(String productName) {
        return false;
    }

    @Override
    public List<Product> getAllProductByTypeId(Integer typeId) {
        return null;
    }
}
