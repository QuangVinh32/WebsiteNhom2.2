package Website2.model.DTO;

import lombok.Data;

import java.util.List;

@Data
public class CartDTOv3 {
    private List<ProductDTO> products;
    private double total;

}
