package Website2.service.Class;

import Website2.model.entity.*;
import Website2.model.request.CreateCart;
import Website2.model.request.UpdateCart;
import Website2.repository.CartRepository;
import Website2.repository.ProductRepository;
import Website2.repository.UserRepository;
import Website2.service.ICartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CartService implements ICartService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Optional<Cart> findByCartId(int id) {
        return cartRepository.findById(id);
    }

    @Transactional
    public void createCart(CreateCart createCart) {
        Cart cart = new Cart();
        // Xử lý userId
        Users users = userRepository.findById(createCart.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + createCart.getUserId()));
        cart.setUsers(users);

        int total = 0;
        List<CartDetail> cartDetails = new ArrayList<>();

        for (CreateCart.CreateCartDetail createCartDetail : createCart.getCreateCartDetails()) {
            CreateCart.CreateCartDetail.ProductRequest productRequest = createCartDetail.getProductRequests();

            Product product = productRepository.findById(productRequest.getIdPro())
                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + productRequest.getIdPro()));

            int count = createCartDetail.getCount();
            int price = product.getPrice();
            double discount = product.getDiscount();

            // Tính tổng cho chi tiết giỏ hàng này
            int detailTotal = (int) (count * price * discount);
            total += detailTotal;

            CartDetailPK cartDetailPK = new CartDetailPK();
            cartDetailPK.setProductId(product);
            cartDetailPK.setCartId(cart);

            CartDetail cartDetail = new CartDetail();
            cartDetail.setCartDetailPK(cartDetailPK);
            cartDetail.setCount(count);
            cartDetails.add(cartDetail);
        }

        cart.setTotal(total);
        cart.setCartDetails(cartDetails);
        cartRepository.save(cart);
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
