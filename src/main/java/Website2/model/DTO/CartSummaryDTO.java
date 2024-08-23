package Website2.model.DTO;

import lombok.Data;

import java.util.List;



    @Data
    public class CartSummaryDTO {
        private List<CartDetailDTO> cartDetails;
        private int total;
    }

