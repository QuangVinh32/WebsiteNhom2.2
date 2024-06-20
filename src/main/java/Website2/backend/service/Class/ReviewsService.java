package Website2.backend.service.Class;

import Website2.backend.repository.ProductRepository;
import Website2.backend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ReviewsService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;
}
