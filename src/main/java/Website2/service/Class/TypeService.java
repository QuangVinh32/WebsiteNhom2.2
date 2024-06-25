package Website2.service.Class;

import Website2.model.entity.Type;
import Website2.model.request.CreateType;
import Website2.model.request.UpdateType;
import Website2.repository.TypeRepository;
import Website2.service.ITypeService;
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
    public Optional<Type> findByTypeId(int typeId) {
        return typeRepository.findById(typeId);
    }

    @Override
    public Type updateType(int typeId, UpdateType updateType) throws Exception {
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
    public void deleteByTypeId(int typeId) {
        typeRepository.deleteById(typeId);
    }


}