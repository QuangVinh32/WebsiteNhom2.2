package Website2.model.DTO;

import Website2.model.entity.Category;
import Website2.model.entity.Nsx;
import Website2.model.entity.ProductStatus;
import Website2.model.entity.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class ProductDTOv2 extends RepresentationModel<ProductDTOv2> {
    private int productId;
    private String productCode;
    private String productName;
    private String descriptionProduct;
    private int price;
    private int discount;
    private String image;
    private ProductStatus status;
    private LocalDateTime createdTime;
    private int soLuongTonKho;
    private List<ReviewsDTO> reviews;
    private Category categoryId;
    private Nsx nsxId;
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReviewsDTO extends RepresentationModel<ReviewsDTO> {
        private String content;
        private int rate;
        private LocalDateTime createTimeReview;
        private UsersDTO users;
        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
         public static class UsersDTO extends RepresentationModel<UsersDTO> {
           private String fullName;
           private String image;
           public static UsersDTO convertToDto(Users users){
              UsersDTO usersDTO = new UsersDTO();
              usersDTO.setFullName(users.getFullName());
              usersDTO.setImage(users.getImage());
              return usersDTO;
         }
    }
    }
}
