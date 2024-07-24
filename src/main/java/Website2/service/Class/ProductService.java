package Website2.service.Class;

import Website2.model.DTO.ProductDTOv2;
import Website2.model.entity.*;
import Website2.model.request.CreateProduct;
import Website2.model.request.FilterProduct;
import Website2.model.request.UpdateProduct;
import Website2.repository.CategoryRepository;
import Website2.repository.NsxRepository;
import Website2.repository.ProductRepository;
import Website2.service.IProductService;
import Website2.speacification.ProductSpecification;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private NsxRepository nsxRepository;
//    @PostConstruct
//    public void configureMapper() {
//        mapper.addMappings(new PropertyMap<UpdateProduct, Product>() {
//            @Override
//            protected void configure() {
//                skip(destination.getProductId());
//                skip(destination.getCategory());
//                skip(destination.getNsx());
//            }
//        });
//    }
@Override
public Product updateProduct(int productId, UpdateProduct updateProduct) throws Exception {
    Product existingProduct = productRepository.findById(productId)
            .orElseThrow(() -> new Exception("Product with ID " + productId + " not found"));

    // Thủ công ánh xạ các trường cần thiết từ updateProduct sang existingProduct
    if (updateProduct.getProductName() != null) {
        existingProduct.setProductName(updateProduct.getProductName());
    }
    if (updateProduct.getDescriptionProduct() != null) {
        existingProduct.setProductDescription(updateProduct.getDescriptionProduct());
    }
    if (updateProduct.getPrice() != 0) {
        existingProduct.setPrice(updateProduct.getPrice());
    }
    if (updateProduct.getDiscount() != 0) {
        existingProduct.setDiscount(updateProduct.getDiscount());
    }
    if (updateProduct.getImage() != null) {
        existingProduct.setImage(updateProduct.getImage());
    }
    if (updateProduct.getStatus() != null) {
        existingProduct.setStatus(updateProduct.getStatus());
    }
    if (updateProduct.getSoLuongTonKho() != 0) {
        existingProduct.setSoLuongTonKho(updateProduct.getSoLuongTonKho());
    }

    // Tìm và thiết lập category nếu có
    if (updateProduct.getCategoryId() != null) {
        Category category = categoryRepository.findById(updateProduct.getCategoryId())
                .orElseThrow(() -> new Exception("Category with ID " + updateProduct.getCategoryId() + " not found"));
        existingProduct.setCategory(category);
    }

    // Tìm và thiết lập nsx nếu có
    if (updateProduct.getNsxId() != null) {
        Nsx nsx = nsxRepository.findById(updateProduct.getNsxId())
                .orElseThrow(() -> new Exception("Nsx with ID " + updateProduct.getNsxId() + " not found"));
        existingProduct.setNsx(nsx);
    }

    return productRepository.save(existingProduct);
}


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> getAllProductsPage(Pageable pageable, FilterProduct filterProduct) {
        Specification<Product> spec = ProductSpecification.buildSpec(filterProduct);
        return productRepository.findAll(spec, pageable);
    }

    @Override
    public ProductDTOv2 getProductById(int id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(this::convertToDto).orElse(null);
    }

    private ProductDTOv2 convertToDto(Product product) {
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

        // Set category and nsx IDs
        if (product.getCategory() != null) {
            productDTO.setCategoryId(product.getCategory().getCategoryId());
        } else {
            productDTO.setCategoryId(null);
        }

        if (product.getNsx() != null) {
            productDTO.setNsxId(product.getNsx().getId());
        } else {
            productDTO.setNsxId(null);
        }

        List<ProductDTOv2.ReviewsDTO> reviewsDTOS = product.getReviews().stream()
                .map(this::reviewsDTO)
                .collect(Collectors.toList());
        productDTO.setReviews(reviewsDTOS);
        return productDTO;
    }

    private ProductDTOv2.ReviewsDTO reviewsDTO(Reviews reviews) {
        ProductDTOv2.ReviewsDTO reviewsDTO = new ProductDTOv2.ReviewsDTO();
        reviewsDTO.setContent(reviews.getContent());
        reviewsDTO.setRate(reviews.getRate());
        reviewsDTO.setCreateTimeReview(reviews.getCreateTimeReview());
        if (reviews.getUser() != null) {
            reviewsDTO.setUsers(ProductDTOv2.ReviewsDTO.UsersDTO.convertToDto(reviews.getUser()));
        }
        return reviewsDTO;
    }

    @Override
    public void createProduct(CreateProduct createProduct) throws Exception {
        Product productDb = mapper.map(createProduct, Product.class);

        // Find category by categoryId
        Category category = categoryRepository.findById(createProduct.getCategoryId())
                .orElseThrow(() -> new Exception("Category with ID " + createProduct.getCategoryId() + " not found"));
        productDb.setCategory(category);

        // Find nsx by nsxId
        Nsx nsx = nsxRepository.findById(createProduct.getNsxId())
                .orElseThrow(() -> new Exception("Nsx with ID " + createProduct.getNsxId() + " not found"));
        productDb.setNsx(nsx);

        productRepository.save(productDb);
    }

//    @Override
//    public Product updateProduct(int productId, UpdateProduct updateProduct) throws Exception {
//        Product existingProduct = productRepository.findById(productId)
//                .orElseThrow(() -> new Exception("Product with ID " + productId + " not found"));
//
//        mapper.map(updateProduct, existingProduct);
//
//        // Find category by categoryId if present in updateProduct
//        if (updateProduct.getCategoryId() != null) {
//            Category category = categoryRepository.findById(updateProduct.getCategoryId())
//                    .orElseThrow(() -> new Exception("Category with ID " + updateProduct.getCategoryId() + " not found"));
//            existingProduct.setCategory(category);
//        }
//
//        // Find nsx by nsxId if present in updateProduct
//        if (updateProduct.getNsxId() != null) {
//            Nsx nsx = nsxRepository.findById(updateProduct.getNsxId())
//                    .orElseThrow(() -> new Exception("Nsx with ID " + updateProduct.getNsxId() + " not found"));
//            existingProduct.setNsx(nsx);
//        }
//        return productRepository.save(existingProduct);
//    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public boolean isProductNameExists(String productName) {
        return false; // Implement as needed
    }

    @Override
    public List<Product> getAllProductByTypeId(Integer typeId) {
        return null; // Implement as needed
    }
}
