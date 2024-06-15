package Website2.backend.model.request;

import Website2.backend.model.entity.Nsx;
import Website2.backend.model.entity.ProductStatus;
import Website2.backend.model.entity.Type;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class ProductRequest {
    private int productId;
    private int productCode;
    private String nameProduct;
    private String descriptionProduct;
    private int price;
    private int discount;
    private String image;
    private ProductStatus status;
    private LocalDateTime createdTime;
    private int soLuongTonKho;

}
