package Website2.backend.service.Class;

import Website2.backend.model.entity.Type;
import Website2.backend.model.request.CreateType;
import Website2.backend.model.request.UpdateType;
import Website2.backend.repository.TypeRepository;
import Website2.backend.service.ITypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class TypeService implements ITypeService {
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public List<Type> findAll() {
        return typeRepository.findAll();
    }

    @Override
    public Optional<Type> findByTypeId(Integer typeId) {
        return typeRepository.findById(typeId);
    }

    @Override
    public Type updateType(Integer typeId, UpdateType updateType) throws Exception {
        Optional<Type> typeDb = findByTypeId(typeId);
        if(typeDb.isPresent()){
            Type existingType = typeDb.get();
            mapper.map(updateType, existingType);
            return typeRepository.save(existingType);
        }else {
            throw new Exception("Type không tìm thấy id:" + typeId);
        }
    }

    @Override
    public void createType(CreateType createType) throws Exception {
        Optional<Type> existingProduct = typeRepository.findById(createType.getTypeId());
            if (existingProduct.isEmpty()){
                throw new Exception("Type không có sản phẩm nào");
            }
            Type typeDb = mapper.map(createType, Type.class);
            typeRepository.save(typeDb);
    }

    @Override
    public void deleteByTypeId(Integer typeId) {
        typeRepository.deleteById(typeId);
    }


}
