package Website2.backend.dto;

import Website2.backend.model.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class TypeDTO {
    private String typeName;

    private String Description;

    private List<Product> products;
}
