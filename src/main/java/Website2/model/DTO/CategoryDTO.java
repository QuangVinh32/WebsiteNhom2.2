package Website2.model.DTO;

import Website2.model.entity.Category;
import lombok.Data;

@Data
public class CategoryDTO {

    private Integer categoryId;

    public CategoryDTO(Category category) {
        this.categoryId = category.getCategoryId();
    }
}