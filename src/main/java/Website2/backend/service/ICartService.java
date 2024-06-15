package Website2.backend.service;

import Website2.backend.Form.CartCreateForm;
import Website2.backend.Form.CartUpdateForm;
import Website2.backend.model.entity.Cart;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ICartService {

    List<Cart> getAllCartByUserId(Integer userId);

    Cart createCart(CartCreateForm form);

    Cart updateCart(CartUpdateForm form);

    void deleteCart(Integer userId, Integer productId);
}
