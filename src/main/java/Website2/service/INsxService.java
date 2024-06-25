package Website2.service;

import Website2.model.entity.Nsx;
import Website2.model.request.CreateNsx;
import Website2.model.request.UpdateNsx;

import java.util.List;
import java.util.Optional;


public interface INsxService {
    List<Nsx> findAll();

    Optional<Nsx> findByNsxId(Integer id);

    void createNsx(CreateNsx createNsx) throws Exception;

    Nsx updateNsx(Integer id, UpdateNsx updateNsx) throws Exception;

    void deleteByNsxId(Integer id);
}