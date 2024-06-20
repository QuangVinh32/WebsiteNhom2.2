package Website2.backend.service;

import Website2.backend.model.entity.CartDetail;
import Website2.backend.model.request.CreateCartDetail;
import Website2.backend.model.request.PkCartDetail;
import Website2.backend.model.request.UpdateCartDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICartDetailService {
    List<CartDetail> findAllCarDetail();
    CartDetail findById(PkCartDetail pkCartDetail);
    void createCartDetail(CreateCartDetail createCartDetail);
    CartDetail updateCartDetail(UpdateCartDetail updateCartDetail);
    void deleteCartDetail(PkCartDetail pkCartDetail);



}
