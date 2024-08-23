package Website2.model.DTO;

import lombok.Data;

@Data
public class ProductDTO {
    private Integer productId;
    private String productName;
    private int price;
    private int discount;
    private int count;

    public ProductDTO(Integer productId, String productName, int price, int discount, int count) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.discount = discount;
        this.count = count;
    }
}