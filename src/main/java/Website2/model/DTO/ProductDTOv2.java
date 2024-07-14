package Website2.model.DTO;

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


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReviewsDTO extends RepresentationModel<ReviewsDTO> {
        private String content;
        private int rate;

        private UsersDTO users;
//
////        private List<Website2.model.DTO.UsersDTO> users;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

      public static class UsersDTO extends RepresentationModel<UsersDTO> {
         private String fullName;
         public static UsersDTO convertToDto(Users users){
             UsersDTO usersDTO = new UsersDTO();
             usersDTO.setFullName(users.getFullName());
             return usersDTO;
         }
    }
    }
}
