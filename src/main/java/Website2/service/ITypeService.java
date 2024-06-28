package Website2.service;


import Website2.model.entity.Type;
import Website2.model.request.CreateType;
import Website2.model.request.UpdateType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public interface ITypeService {
    List<Type> findAll();

    Optional<Type> findByTypeId(int typeId);

    Type updateType(int typeId, UpdateType updateType) throws Exception;

    void createType(CreateType createType) throws Exception;

    void deleteByTypeId(int typeId);
}
