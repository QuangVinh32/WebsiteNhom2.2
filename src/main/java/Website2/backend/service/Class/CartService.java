package Website2.backend.service.Class;
import Website2.backend.model.entity.Cart;
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

    }

    @Override
    public Cart updateCart(Integer id, UpdateCart updateCart) throws Exception {
        return null;
    }

    @Override
    public void deleteByCartId(int id) {
        cartRepository.deleteById(id);

    }
}
