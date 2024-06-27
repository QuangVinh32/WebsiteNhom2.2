package Website2.controller;

import Website2.model.DTO.ProductDTO;
import Website2.model.entity.Product;
import Website2.model.request.CreateProduct;
import Website2.model.request.UpdateProduct;
import Website2.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<ProductDTO> findAllPhong() {
        List<Product> phongs = productService.getAllProducts();
        List<ProductDTO> productDTOS = phongs.stream()
                .map(phong -> mapper.map(phong, ProductDTO.class))
                .collect(Collectors.toList());
        return productDTOS;
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
    public ProductDTO findProductById(@PathVariable("id") int id ){
        Optional<Product> product = productService.getProductById(id);
        return mapper.map(product,ProductDTO.class);
    }
    @GetMapping("/find-by-typeid/{id}")
    public ProductDTO getAllProductByTypeId(@PathVariable("id") int id ){
        List<Product> product = productService.getAllProductByTypeId(id);
        return mapper.map(product,ProductDTO.class);
    }

}