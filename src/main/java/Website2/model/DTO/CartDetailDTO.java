package Website2.model.DTO;
import lombok.Data;
@Data
public class CartDetailDTO {
    private String productName;
    private String productImage;
    private int price;
    private int discount;
    private int count;
    private int totalPrice;
}

