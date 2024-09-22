package Website2.controller;
import Website2.model.DTO.CartDTO;

import Website2.model.DTO.CartSummaryDTO;
import Website2.model.entity.Cart;
import Website2.model.entity.CartDetail;
import Website2.model.entity.Users;
import Website2.model.request.CreateCart;
import Website2.model.request.UpdateCart;
import Website2.service.ICartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/cart")
@CrossOrigin("*")
@Validated
public class CartController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ICartService cartService;


    @PostMapping("/add/{productId}")
    public ResponseEntity<?> addProductToCart(@PathVariable Integer productId) {
        cartService.addProductToCart(productId);
        return ResponseEntity.ok("Product added to cart");
    }



    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<String> removeProductFromCart(@PathVariable Integer productId) {
        try {
            cartService.removeProductFromCart(productId);
            return ResponseEntity.ok("Xóa sản phẩm khỏi giỏ hàng thành công.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/summary")
    public CartSummaryDTO getCartSummary() {
        return cartService.getCartSummary();
    }

    @GetMapping("/find-all-cart")
    List<CartDTO> findAll() {
        List<Cart> carts = cartService.findAll();
        List<CartDTO> cartDTOS = carts.stream()
                .map(hoaDon -> mapper.map(carts, CartDTO.class))
                .collect(Collectors.toList());
        return cartDTOS;
    }
    @PostMapping("/create-cart")
    public ResponseEntity<?> createCart(@RequestBody CreateCart createCart) throws Exception {
        cartService.createCart(createCart);
        return ResponseEntity.ok("Thêm giỏ hàng thành công");
    }
    @PutMapping("/update-cart/{id}")
    public ResponseEntity<?> updateCart(@PathVariable int id,@RequestBody UpdateCart updateCart) throws Exception {
        cartService.updateCart(id,updateCart);
        return ResponseEntity.ok("Sửa giỏ hàng thành công");
    }
    @DeleteMapping("/delete-cart/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable("id") int id){
        cartService.deleteByCartId(id);
        return ResponseEntity.ok("Xóa giỏ hàng thàng công");
    }
    @GetMapping("/find-by-id/{id}")
    public CartDTO findCartById(@PathVariable("id") int id ){
        Optional<Cart> cart = cartService.findByCartId(id);
        return mapper.map(cart,CartDTO.class);
    }


//    @GetMapping("/details/{username}")
//    public List<Cart> getCartForUser(@PathVariable String username) {
//            List<Cart> cart =  cartService.getCartForUser(username);
//        return cart ;
//    }
//
//    @GetMapping("/infor/{username}")
//    public List<CartDetail> getCartDetailsForUser( @PathVariable String username) {
//        List<CartDetail> cartDetails = cartService.getCartDetailsForUser(username);
//        return cartDetails;
//    }



}
