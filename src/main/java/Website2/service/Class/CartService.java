package Website2.service.Class;

import Website2.model.entity.*;
import Website2.model.request.CreateCart;
import Website2.model.request.UpdateCart;
import Website2.repository.CartDetailRepository;
import Website2.repository.CartRepository;
import Website2.repository.ProductRepository;
import Website2.repository.UserRepository;
import Website2.service.ICartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    protected CartDetailRepository cartDetailRepository;

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

    @Override
    public List<Cart> getCartForUser(String username) {
        Optional<Users> users = userRepository.findByUsername(username);
        return cartRepository.findAllByUsers(users.get());
    }

    @Override
    public List<CartDetail> getCartDetailsForUser(String username) {
        List<Cart> carts = getCartForUser(username);
        List<CartDetail> cartDetails = new ArrayList<>();
        for (Cart cart1: carts){
            List<CartDetail> details = cartDetailRepository.findAllByCart(cart1);
            cartDetails.addAll(details);
        }
        return cartDetails;
    }


    // Phương thức thêm sản phẩm vào giỏ hàng
    public void addProductToCart(Integer cartId, Integer productId) {
        // Tìm Cart theo cartId
        Cart cart = cartRepository.findById(cartId).orElse(null);

        if (cart == null) {
            // Nếu không tìm thấy Cart, tạo mới
            cart = new Cart();
            cart.setTotal(0); // Khởi tạo tổng tiền là 0
            // Lấy thông tin người dùng hiện tại từ SecurityContext
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Users user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            cart.setUsers(user);
            cart = cartRepository.save(cart); // Lưu Cart mới
        }

        // Tìm Product theo productId
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Tạo CartDetailPK và tìm CartDetail hiện tại
        CartDetailPK cartDetailPK = new CartDetailPK(cart, product);
        Optional<CartDetail> existingCartDetail = cartDetailRepository.findById(cartDetailPK);

        if (existingCartDetail.isPresent()) {
            CartDetail cartDetail = existingCartDetail.get();
            cartDetail.setCount(cartDetail.getCount() + 1);
            cartDetailRepository.save(cartDetail);
        } else {
            CartDetail newCartDetail = new CartDetail();
            newCartDetail.setCartDetailPK(cartDetailPK);
            newCartDetail.setCount(1);
            newCartDetail.setCart(cart);
            newCartDetail.setProduct(product);
            cartDetailRepository.save(newCartDetail);
        }

        // Cập nhật tổng tiền của giỏ hàng
        updateCartTotal(cart);
    }

    // Phương thức xoá sản phẩm khỏi giỏ hàng
    public void removeProductFromCart(Integer cartId, Integer productId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartDetailPK cartDetailPK = new CartDetailPK(cart, product);
        Optional<CartDetail> existingCartDetail = cartDetailRepository.findById(cartDetailPK);

        if (existingCartDetail.isPresent()) {
            CartDetail cartDetail = existingCartDetail.get();
            int newCount = cartDetail.getCount() - 1;

            if (newCount > 0) {
                // Cập nhật số lượng và lưu lại
                cartDetail.setCount(newCount);
                cartDetailRepository.save(cartDetail);
            } else {
                // Xóa CartDetail nếu số lượng bằng 0
                cartDetailRepository.delete(cartDetail);
            }

            // Cập nhật tổng tiền của giỏ hàng
            updateCartTotal(cart);
        } else {
            throw new RuntimeException("CartDetail not found");
        }
    }

    // Phương thức cập nhật tổng tiền của giỏ hàng
    private void updateCartTotal(Cart cart) {
        List<CartDetail> cartDetails = cart.getCartDetails();
        if (cartDetails == null || cartDetails.isEmpty()) {
            cartDetails = cartDetailRepository.findByCart(cart); // Nếu danh sách null hoặc rỗng, lấy lại từ DB
        }

        double cartTotal = cartDetails.stream()
                .mapToDouble(cd -> cd.getProduct().getPrice() * ((100 - cd.getProduct().getDiscount()) / 100.0) * cd.getCount())
                .sum();

        cart.setTotal((int) cartTotal);
        cartRepository.save(cart);
    }








//                                      ADD
    // Method to add or increase the quantity of a product in the cart
//    public void addProductToCart(Integer cartId, Integer productId) {
//        Cart cart = cartRepository.findById(cartId)
//                .orElseThrow(() -> new RuntimeException("Cart not found"));
//
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new RuntimeException("Product not found"));
//
//        CartDetailPK cartDetailPK = new CartDetailPK(cart, product);
//        Optional<CartDetail> existingCartDetail = cartDetailRepository.findById(cartDetailPK);
//
//        if (existingCartDetail.isPresent()) {
//            CartDetail cartDetail = existingCartDetail.get();
//            cartDetail.setCount(cartDetail.getCount() + 1);
//            cartDetailRepository.save(cartDetail);
//        } else {
//            CartDetail newCartDetail = new CartDetail();
//            newCartDetail.setCartDetailPK(cartDetailPK);
//            newCartDetail.setCount(1);
//            newCartDetail.setCart(cart);
//            newCartDetail.setProduct(product);
//            cartDetailRepository.save(newCartDetail);
//        }
//    }




//                                             REMOVE
//    // Method to decrease the quantity of a product in the cart or remove it if quantity reaches 0
//    public void removeProductFromCart(Integer cartId, Integer productId) {
//        Cart cart = cartRepository.findById(cartId)
//                .orElseThrow(() -> new RuntimeException("Cart not found"));
//
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new RuntimeException("Product not found"));
//
//        CartDetailPK cartDetailPK = new CartDetailPK(cart, product);
//        CartDetail cartDetail = cartDetailRepository.findById(cartDetailPK)
//                .orElseThrow(() -> new RuntimeException("CartDetail not found"));
//
//        if (cartDetail.getCount() > 1) {
//            cartDetail.setCount(cartDetail.getCount() - 1);
//            cartDetailRepository.save(cartDetail);
//        } else {
//            cartDetailRepository.delete(cartDetail);
//        }
//    }

    //                        CREATE CART

    //    @Transactional
//    public void createCart(CreateCart createCart) {
//        Cart cart = new Cart();
//
//        Users users = userRepository.findById(createCart.getUserId())
//                .orElseThrow(() -> new RuntimeException("User not found with id: " + createCart.getUserId()));
//        cart.setUsers(users);
//
//        int total = 0;
//        List<CartDetail> cartDetails = new ArrayList<>();
//
//        for (CreateCart.CreateCartDetail createCartDetail : createCart.getCreateCartDetails()) {
//            CreateCart.CreateCartDetail.ProductRequest productRequest = createCartDetail.getProductRequests();
//
//            Product product = productRepository.findById(productRequest.getIdPro())
//                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + productRequest.getIdPro()));
//
//            int count = createCartDetail.getCount();
//            int price = product.getPrice();
//            double discount = product.getDiscount();
//
//            int detailTotal = (int) (count * price * discount/100);
//            total += detailTotal;
//
//            CartDetailPK cartDetailPK = new CartDetailPK();
//            cartDetailPK.setProductId(product);
//            cartDetailPK.setCartId(cart);
//
//            CartDetail cartDetail = new CartDetail();
//            cartDetail.setCartDetailPK(cartDetailPK);
//            cartDetail.setCount(count);
//            cartDetails.add(cartDetail);
//        }
//
//        cart.setTotal(total);
//        cart.setCartDetails(cartDetails);
//        cartRepository.save(cart);
//    }

}



