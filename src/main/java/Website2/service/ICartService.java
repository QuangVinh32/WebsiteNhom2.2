package Website2.service;


import Website2.model.entity.Cart;
import Website2.model.request.CreateCart;
import Website2.model.request.UpdateCart;

import java.util.List;
import java.util.Optional;


public interface ICartService {
    List<Cart> findAll();

    Optional<Cart> findByCartId(int id);

    void createCart(CreateCart createCart) throws Exception;

    Cart updateCart(int id, UpdateCart updateCart) throws Exception;

    void deleteByCartId(int id);


}