package Website2.backend.model.request;

import Website2.backend.model.entity.ProductStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
public class CreateProduct {
    private int productId;
    private int productCode;
    private String productName;
    private String descriptionProduct;
    private int price;
    private int discount;
    private String image;
    private ProductStatus status;
    private LocalDateTime createdTime;
    private int soLuongTonKho;
}
