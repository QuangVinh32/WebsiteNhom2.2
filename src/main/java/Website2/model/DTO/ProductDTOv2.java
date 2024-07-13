package Website2.model.DTO;

import Website2.model.entity.ProductStatus;
import Website2.model.entity.Reviews;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class ProductDTOv2 extends RepresentationModel<ProductDTOv2> {
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
    private List<ReviewsDTO> reviews;
    @Getter
    @Setter
    public static class ReviewsDTO extends RepresentationModel<ReviewsDTO> {
        private String content;
        private int rate;
    }
}
