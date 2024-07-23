package Website2.model.DTO;

import Website2.model.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {

    private Integer categoryId;

    private String categoryName;

    private String Description;

//    private List<Product> products;
}