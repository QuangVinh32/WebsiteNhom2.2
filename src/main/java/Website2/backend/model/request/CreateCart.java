package Website2.backend.model.request;

import lombok.Data;

@Data
public class CreateCart {
    private int cartId;
    private int total;
}
