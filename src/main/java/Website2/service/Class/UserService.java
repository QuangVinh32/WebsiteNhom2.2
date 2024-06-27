package Website2.service.Class;

import Website2.model.entity.Cart;
import Website2.model.request.CreateCart;
import Website2.model.request.UpdateCart;
import Website2.service.ICartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements ICartService {
    @Override
    public List<Cart> findAll() {
        return null;
    }

    @Override
    public Optional<Cart> findByCartId(int id) {
        return Optional.empty();
    }

    @Override
    public void createCart(CreateCart createCart) throws Exception {

    }

    @Override
    public Cart updateCart(int id, UpdateCart updateCart) throws Exception {
        return null;
    }

    @Override
    public void deleteByCartId(int id) {

    }
}
