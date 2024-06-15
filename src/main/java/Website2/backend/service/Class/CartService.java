package Website2.backend.service.Class;

import Website2.backend.Form.CartCreateForm;
import Website2.backend.Form.CartUpdateForm;
import Website2.backend.model.entity.Cart;
import Website2.backend.repository.CartRepository;
import Website2.backend.service.ICartService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;

@Service
public class CartService implements ICartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Cart> getAllCartByUserId(Integer userId) {
        return cartRepository.fingByUserId(userId);
    }

    @Override
    @Transactional
    public Cart createCart(CartCreateForm form) {
        Cart cart = modelMapper.map(form , Cart.class);
        Cart.CartPK cartId = new Cart.CartPK();
        cartId.setProductId(form.getProductId());
        cartId.setUserId(form.getUserId());
        cart.setCartId(cartId);
        return cartRepository.save(cart);
    }

    @Override
    @Transactional
    public Cart updateCart(CartUpdateForm form) {
        Cart.CartPK id = new Cart.CartPK();
        id.setProductId(form.getProductId());
        id.setUserId(form.getUserId());
        Cart cart = cartRepository.findById(id).get();
        return null;
    }

    @Override
    public void deleteCart(Integer userId, Integer productId) {

    }
}
