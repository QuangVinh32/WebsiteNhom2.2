package Website2.controller;
import Website2.model.DTO.CartDetailDTO;
import Website2.model.entity.CartDetail;
import Website2.model.request.CreateCartDetail;
import Website2.model.request.PkCartDetail;
import Website2.model.request.UpdateCartDetail;
import Website2.service.ICartDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("api/v1/cartdetail")
@CrossOrigin("*")
@Validated
public class CartDetailController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ICartDetailService cartDetailService;

    @GetMapping("/find-all-cartdetail")
    public List<CartDetailDTO> findAllCartDetail(){
        List<CartDetail> cartDetails = cartDetailService.findAllCarDetail();
        List<CartDetailDTO> cartDetailDTOS = cartDetails.stream()
                .map(cartDetail -> mapper.map(cartDetail, CartDetailDTO.class))
                .collect(Collectors.toList());
        return cartDetailDTOS;
    }
    @GetMapping("/find-cartdetail-by-id")
    public CartDetailDTO findCartDetailById(@RequestBody PkCartDetail request) {
        CartDetail cartDetail = cartDetailService.findById(request);
        return mapper.map(cartDetail , CartDetailDTO.class);
    }
    @PostMapping("/create-cartdetail")
    public ResponseEntity<?> createCartDetail(@Valid @RequestBody CreateCartDetail request){
        cartDetailService.createCartDetail(request);
        return ResponseEntity.ok("Thêm Chi tiết giỏ hàng thành công");
    }
    @PutMapping ("/update-cartdetail")
    public ResponseEntity<?> updateCartDetail(@Valid @RequestBody UpdateCartDetail request){
        cartDetailService.updateCartDetail(request);
        return ResponseEntity.ok("Sửa Chi tiết giỏ hàng thành công");
    }
    @DeleteMapping("/delete-cartdetail")
    public ResponseEntity<?> deleteCartDetail(@RequestBody PkCartDetail request){
        cartDetailService.deleteCartDetail(request);
//        SecurityContextHolder.getContext().getAuthentication()
        return ResponseEntity.ok("Xóa Chi tiết giỏ hàng thành công");
    }

    // update so luong(them gio hang+1)-- truyen vao  productId, cartID:  useridlogin = SecurityContextHolder.getContext().getAuthentication().getName();
    // service :
    // chua co:   carDetail (carId, productId)  where  carId= ? and productId =?  -> new CarDetail
    // da co:  carDetail (carId, productId)  where  carId= ? and productId =?-> cartDetail +1 so luong

    // update so luong khi giam so luong o gio hang  truyen vao  productId, cartID
    // service :
    // chua co:   carDetail (carId, productId)  where  carId= ? and productId =?  -> bao loi
    // da co:  carDetail (carId, productId)  where  carId= ? and productId =?-> cartDetail -1 so luong>0
    //         cartDetail -1 so luong == 0-> xoa
}
