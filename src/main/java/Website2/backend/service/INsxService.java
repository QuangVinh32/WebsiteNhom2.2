package Website2.backend.service;

import Website2.backend.model.entity.Nsx;
import Website2.backend.model.entity.Type;
import Website2.backend.model.request.CreateNsx;
import Website2.backend.model.request.UpdateNsx;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface INsxService {
    List<Nsx> findAll();

    Optional<Nsx> findByNsxId(Integer id);

    void createNsx(CreateNsx createNsx) throws Exception;

    Nsx updateNsx(Integer id, UpdateNsx updateNsx) throws Exception;

    void deleteByNsxId(Integer id);
}
