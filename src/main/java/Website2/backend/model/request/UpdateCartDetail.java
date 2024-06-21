package Website2.backend.model.request;

import Website2.backend.model.entity.CartDetailPK;
import lombok.Data;

@Data
public class UpdateCartDetail {
    private CartDetailPK cartDetailPK;
    private int count;
}
