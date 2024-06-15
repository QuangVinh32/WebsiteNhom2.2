package Website2.backend.service.Class;

import Website2.backend.model.entity.Nsx;
import Website2.backend.model.entity.Order;
import Website2.backend.model.request.CreateNsx;
import Website2.backend.model.request.CreateOrder;
import Website2.backend.model.request.UpdateNsx;
import Website2.backend.repository.NsxRepository;
import Website2.backend.service.INsxService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NsxService implements INsxService {
    @Autowired
    private NsxRepository nsxRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Nsx> findAll() {
        return nsxRepository.findAll();
    }

    @Override
    public Optional<Nsx> findByNsxId(Integer id) {
        return nsxRepository.findById(id);
    }

    @Override
    public void createNsx(CreateNsx createNsx) throws Exception {
        Optional<Nsx> existingProduct = nsxRepository.findById(createNsx.getId());
        if (existingProduct.isEmpty()){
            throw new Exception("Không có sản phẩm nào thuộc nhà sản xuất");
        }
        Nsx nsxDb = modelMapper.map(createNsx, Nsx.class);
        nsxRepository.save(nsxDb);
    }

    @Override
    public Nsx updateNsx(Integer id, UpdateNsx updateNsx) throws Exception {
        Optional<Nsx> nsxDb = findByNsxId(id);
        if (nsxDb.isPresent()){
            Nsx existingNsx = nsxDb.get();
            modelMapper.map(updateNsx, existingNsx);
            return nsxRepository.save(existingNsx);
        }else {
            throw new Exception("NSX không tìm thấy id");
        }

    }


    @Override
    public void deleteByNsxId(Integer id) {
        nsxRepository.deleteById(id);
    }
}
