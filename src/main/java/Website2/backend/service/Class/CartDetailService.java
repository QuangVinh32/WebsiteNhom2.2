package Website2.backend.service.Class;

import Website2.backend.model.entity.*;
import Website2.backend.model.request.CreateCartDetail;
import Website2.backend.model.request.PkCartDetail;
import Website2.backend.model.request.UpdateCartDetail;
import Website2.backend.repository.CartRepository;
import Website2.backend.repository.OrderDetailRepository;
import Website2.backend.repository.ProductRepository;
import Website2.backend.service.ICartDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartDetailService implements ICartDetailService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<OrderDetail> findAllCarDetail() {
        return orderDetailRepository.findAll();
    }

    @Override
    public CartDetail findById(PkCartDetail pkCartDetail) {
        return null;
    }

    @Override
    public void createCartDetail(CreateCartDetail createCartDetail) {

    }

    @Override
    public CartDetail updateCartDetail(UpdateCartDetail updateCartDetail) {
        return null;
    }

    @Override
    public void deleteCartDetail(PkCartDetail pkCartDetail) {

    }


}
