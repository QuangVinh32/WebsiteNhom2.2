package Website2.backend.service;


import Website2.backend.model.entity.Cart;
import Website2.backend.model.entity.Nsx;
import Website2.backend.model.request.CreateCart;
import Website2.backend.model.request.CreateNsx;
import Website2.backend.model.request.UpdateCart;
import Website2.backend.model.request.UpdateNsx;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ICartService {
    List<Cart> findAll();

    Optional<Cart> findByCartId(int id);

    void createCart(CreateCart createCart) throws Exception;

    Cart updateCart(Integer id, UpdateCart updateCart) throws Exception;

    void deleteByCartId(int id);


}
