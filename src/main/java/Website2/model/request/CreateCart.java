package Website2.model.request;

import lombok.Data;

import java.util.List;
@Data
public class CreateCart {
    private int total;
    private Integer userId;
    private List<CreateCartDetail> createCartDetails;
    @Data
    public static class CreateCartDetail {
        private int count;
        //id cart-detail
        private ProductRequest productRequests;
        @Data
        public static class ProductRequest {
            private Integer idPro;
            private Integer price;
            private Integer discount;
        }
    }
}