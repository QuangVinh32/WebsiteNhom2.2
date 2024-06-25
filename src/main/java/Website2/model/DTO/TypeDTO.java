package Website2.model.DTO;

import Website2.model.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class TypeDTO {
    private String typeName;

    private String Description;

    private List<Product> products;
}