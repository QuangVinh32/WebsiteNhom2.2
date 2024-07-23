package Website2.controller;


import Website2.model.DTO.CategoryDTO;
import Website2.model.DTO.ProductDTO;
import Website2.model.entity.Category;
import Website2.model.entity.Product;
import Website2.model.request.CreateCategory;
import Website2.model.request.FilterCategory;
import Website2.model.request.FilterProduct;
import Website2.model.request.UpdateCategory;
import Website2.service.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/categorys")
@CrossOrigin("*")
@Validated
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private ModelMapper mapper;

//    @GetMapping("/get-all-categorys")
//    List<CategoryDTO> findAllCategory() {
//        List<Category> types = categoryService.findAll();
//        List<CategoryDTO> categoryDTOS = types.stream()
//                .map(type -> mapper.map(type, CategoryDTO.class))
//                .collect(Collectors.toList());
//
//        return categoryDTOS;
//    }
@GetMapping("/find-all-category")
    public Page<CategoryDTO> findAllCategoryPage(Pageable pageable, FilterCategory filterCategory) {
    Page<Category> categoryPage = categoryService.getAllCategoryPage(pageable,filterCategory);
    return categoryPage.map(category -> mapper.map(category, CategoryDTO.class));
    }

    @PostMapping("/create-category")
    public ResponseEntity<?> createCategory(@RequestBody CreateCategory createCategory){
        categoryService.createCategory(createCategory);
        return ResponseEntity.ok("Thêm loại thành công");
    }
    @PutMapping("/update-category/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id,@RequestBody UpdateCategory updateCategory) throws Exception {
        categoryService.updateCategory(id,updateCategory);
        return ResponseEntity.ok("Sửa loại thành công");
    }
    @DeleteMapping("/delete-category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Integer id){
        categoryService.deleteByCategoryId(id);
        return ResponseEntity.ok("Xóa loại thàng công");
    }
}
