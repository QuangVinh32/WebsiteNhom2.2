package Website2.backend.service;


import Website2.backend.model.entity.Type;
import Website2.backend.model.request.CreateType;
import Website2.backend.model.request.UpdateType;

import java.util.List;
import java.util.Optional;

public interface ITypeService {
    List<Type> findAll();

    Optional<Type> findByTypeId(Integer typeId);

    Type updateType(Integer typeId, UpdateType updateType) throws Exception;

    void createType(CreateType createType) throws Exception;

    void deleteByTypeId(Integer typeId);
}
