package Website2.controller;
import Website2.model.DTO.ProductForAdmin;
import Website2.model.DTO.ProductForUser;
import Website2.model.DTO.ProductFindAll;
import Website2.model.entity.Product;
import Website2.model.request.CreateProduct;
import Website2.model.request.FilterProduct;
import Website2.model.request.UpdateProduct;
import Website2.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin("*")
@Validated
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private ModelMapper mapper;

     @GetMapping("/find-all-product")
     public Page<ProductFindAll> findAllProductPage(Pageable pageable, FilterProduct filterProduct) {
            Page<Product> productsPage = productService.getAllProductsPage(pageable,filterProduct);
            return productsPage.map(product -> mapper.map(product, ProductFindAll.class));
}

    @PostMapping("/create-product")
    public ResponseEntity<?> createProduct(@RequestBody CreateProduct createProduct) throws Exception {
        productService.createProduct(createProduct);
        return ResponseEntity.ok("Thêm sản phẩm thành công");
    }
    @PutMapping("/update-product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id,@RequestBody UpdateProduct updateProduct) throws Exception {
        productService.updateProduct(id,updateProduct);
        return ResponseEntity.ok("Update sản phẩm thành công");
    }
    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") int id){
        productService.deleteProduct(id);
        return ResponseEntity.ok("Xóa Sản phẩm thành công");
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<ProductForUser> findProductById(@PathVariable("id") int id) {
        System.out.println("Fetching product with ID: " + id);
        ProductForUser productDTOv2 = productService.getProductById(id);
        if (productDTOv2 != null) {
            return ResponseEntity.ok(productDTOv2);
        } else {
            System.out.println("Product with ID " + id + " not found");
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/find-by-id/v1/{id}")
    public ResponseEntity<ProductForAdmin> findProductByIdOld(@PathVariable("id") int id) {
        System.out.println("Fetching product with ID: " + id);

        ProductForAdmin productDTO = productService.getProductByIdOld(id);

        if (productDTO != null) {
            return ResponseEntity.ok(productDTO);
        } else {
            System.out.println("Product with ID " + id + " not found");
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/find-by-typeid/{id}")
    public ProductForAdmin getAllProductByTypeId(@PathVariable("id") int id ){
        List<Product> product = productService.getAllProductByTypeId(id);
        return mapper.map(product, ProductForAdmin.class);
    }

}