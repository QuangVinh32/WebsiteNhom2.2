package Website2.backend.service.Class;
import Website2.backend.model.entity.Cart;
import Website2.backend.model.entity.Product;
import Website2.backend.model.request.CreateCart;
import Website2.backend.model.request.UpdateCart;
import Website2.backend.repository.CartRepository;
import Website2.backend.service.ICartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CartService implements ICartService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CartRepository cartRepository;


    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Optional<Cart> findByCartId(int id) {
        return cartRepository.findById(id);
    }

    @Override
    public void createCart(CreateCart createCart) throws Exception {
        Optional<Cart> existingCart = cartRepository.findById(createCart.getCartId());
        if (existingCart.isEmpty()){
            throw new Exception(".....");
        }
        Cart cartDb = mapper.map(createCart, Cart.class);
        cartRepository.save(cartDb);
    }

    @Override
    public Cart updateCart(int id, UpdateCart updateCart) throws Exception {
        Optional<Cart> cartDb = findByCartId(id);
        if (cartDb.isPresent()){
            Cart existingCart = cartDb.get();
            mapper.map(updateCart, existingCart);
            return cartRepository.save(existingCart);
        }else {
            throw new Exception("Product không tìm thấy id");
        }
    }

    @Override
    public void deleteByCartId(int id) {
        cartRepository.deleteById(id);

    }
}
