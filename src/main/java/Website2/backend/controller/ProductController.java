package Website2.backend.controller;

import Website2.backend.dto.ProductDTO;
import Website2.backend.model.entity.Product;
import Website2.backend.model.request.CreateProduct;
import Website2.backend.model.request.UpdateProduct;
import Website2.backend.service.IProductService;
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
    public ResponseEntity<?> updateProduct(@PathVariable Integer id,@RequestBody UpdateProduct updateProduct) throws Exception {
        productService.updateProduct(id,updateProduct);
        return ResponseEntity.ok("Update sản phẩm thành công");
    }
    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Integer id){
        productService.deleteProduct(id);
        return ResponseEntity.ok("Xóa Sản phẩm thành công");
    }
    @GetMapping("/find-by-id/{id}")
    public ProductDTO findProductById(@PathVariable("id") Integer id ){
        Optional<Product> product = productService.getProductById(id);
        return mapper.map(product,ProductDTO.class);
    }
    @GetMapping("/find-by-typeid/{id}")
    public ProductDTO getAllProductByTypeId(@PathVariable("id") Integer id ){
        List<Product> product = productService.getAllProductByTypeId(id);
        return mapper.map(product,ProductDTO.class);
    }

}
