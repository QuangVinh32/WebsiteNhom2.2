package Website2.service.Class;

import Website2.model.DTO.CartDetailDTO;
import Website2.model.DTO.CartSummaryDTO;
import Website2.model.DTO.ProductDTO;
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

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CartService implements ICartService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private HttpSession session;
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

//    @Override
//    public List<Cart> getCartForUser(String username) {
//        Optional<Users> users = userRepository.findByUsername(username);
//        return cartRepository.findAllByUsers(users.get());
//    }
//
//    @Override
//    public List<CartDetail> getCartDetailsForUser(String username) {
//        List<Cart> carts = getCartForUser(username);
//        List<CartDetail> cartDetails = new ArrayList<>();
//        for (Cart cart1: carts){
//            List<CartDetail> details = cartDetailRepository.findAllByCart(cart1);
//            cartDetails.addAll(details);
//        }
//        return cartDetails;
//    }



    public void addProductToCart(Integer productId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Users user = null;
        Cart cart = null;

        if (username != null && !username.equalsIgnoreCase("anonymousUser")) {
            user = userRepository.findByUsername(username).orElse(null);

            cart = cartRepository.findByUsers(user).orElse(null);
        } else {
            cart = (Cart) session.getAttribute("cart");

            if (cart == null) {
                cart = new Cart();
                cart.setTotal(0);
                session.setAttribute("cart", cart); // Lưu giỏ hàng vào session
            }
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

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

        updateCartTotal(cart);
    }

    public void removeProductFromCart(Integer productId) {
        // Lấy thông tin người dùng hiện tại từ SecurityContext
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

//        // Fetch the user by username
//        Users user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));

        Users user = null;
        if (username != null) {
            user = userRepository.findByUsername(username).orElse(null);
        }

        // Fetch the cart for the user
        Cart cart = cartRepository.findByUsers(user)
                .orElseThrow(() -> new RuntimeException("Cart not found for user"));

        // Find the product by productId
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Create CartDetailPK and find the current CartDetail
        CartDetailPK cartDetailPK = new CartDetailPK(cart, product);
        Optional<CartDetail> existingCartDetail = cartDetailRepository.findById(cartDetailPK);

        if (existingCartDetail.isPresent()) {
            CartDetail cartDetail = existingCartDetail.get();
            int newCount = cartDetail.getCount() - 1;

            if (newCount > 0) {
                // Update quantity and save
                cartDetail.setCount(newCount);
                cartDetailRepository.save(cartDetail);
            } else {
                // Delete CartDetail if quantity is 0
                cartDetailRepository.delete(cartDetail);
            }

            // Check if the cart is empty
            List<CartDetail> remainingCartDetails = cartDetailRepository.findByCart(cart);
            if (remainingCartDetails.isEmpty()) {
                // If the cart has no remaining products, delete the cart
                cartRepository.delete(cart);
            } else {
                // Update the total of the cart
                updateCartTotal(cart);
            }
        } else {
            throw new RuntimeException("CartDetail not found");
        }
    }

//    public Cart findCartByUser(Users user) {
//        return cartRepository.findByUsers(user)
//                .orElseThrow(() -> new RuntimeException("Cart not found for user: " + user.getUsername()));
//    }




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
    @Override
    public CartSummaryDTO getCartSummary() {
        // Get the current username from the SecurityContext
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Fetch the user by username
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Fetch the cart for the user
        List<Cart> carts = cartRepository.findAllByUsers(user);

        if (carts.isEmpty()) {
            throw new RuntimeException("Cart not found for user");
        }

        Cart cart = carts.get(0);  // Assuming a single cart per user
        // Fetch cart details
        List<CartDetailDTO> cartDetails = cart.getCartDetails().stream()
                .map(cartDetail -> {
                    CartDetailDTO dto = new CartDetailDTO();
                    dto.setProductName(cartDetail.getProduct().getProductName());
                    dto.setProductImage(cartDetail.getProduct().getImage());
                    dto.setPrice(cartDetail.getProduct().getPrice());
                    dto.setDiscount(cartDetail.getProduct().getDiscount());
                    dto.setCount(cartDetail.getCount());
                    int totalPrice = (int) (cartDetail.getProduct().getPrice() * ((100 - cartDetail.getProduct().getDiscount()) / 100.0) * cartDetail.getCount());
                    dto.setTotalPrice(totalPrice);
                    return dto;
                })
                .collect(Collectors.toList());

        // Calculate the total price for the cart
        int total = cartDetails.stream()
                .mapToInt(CartDetailDTO::getTotalPrice)
                .sum();

        // Create CartSummaryDTO and set values
        CartSummaryDTO cartSummaryDTO = new CartSummaryDTO();
        cartSummaryDTO.setCartDetails(cartDetails);
        cartSummaryDTO.setTotal(total);

        return cartSummaryDTO;
    }














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



