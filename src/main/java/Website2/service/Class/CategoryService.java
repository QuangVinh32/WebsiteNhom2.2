package Website2.service.Class;

import Website2.model.entity.Category;
import Website2.model.request.CreateCategory;
import Website2.model.request.UpdateCategory;
import Website2.repository.CategoryRepository;
import Website2.service.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findByCategoryId(int categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public Category updateCategory(int categoryId, UpdateCategory updateCategory) throws Exception {
        Optional<Category> categoryDb = findByCategoryId(categoryId);
        if(categoryDb.isPresent()){
            Category existingType = categoryDb.get();
            mapper.map(updateCategory, existingType);
            return categoryRepository.save(existingType);
        }else {
            throw new Exception("Type không tìm thấy id:" + categoryId);
        }
    }

    @Override
    public void createCategory(CreateCategory createCategory)  {

            Category categoryDb = mapper.map(createCategory, Category.class);
            categoryRepository.save(categoryDb);
    }

    @Override
    public void deleteByCategoryId(int categoryId) {
        categoryRepository.deleteById(categoryId);
    }


}
