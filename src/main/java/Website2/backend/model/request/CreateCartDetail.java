package Website2.backend.model.request;
import lombok.Data;

@Data
public class CreateCartDetail {
        private int cartId;
        private int productId;
        private int count;

}
