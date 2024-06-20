package Website2.backend.controller;

import Website2.backend.dto.NsxDTO;
import Website2.backend.dto.TypeDTO;
import Website2.backend.model.entity.Nsx;
import Website2.backend.model.entity.Type;
import Website2.backend.model.request.CreateNsx;
import Website2.backend.model.request.CreateType;
import Website2.backend.model.request.UpdateNsx;
import Website2.backend.model.request.UpdateType;
import Website2.backend.service.INsxService;
import Website2.backend.service.ITypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/type")
@CrossOrigin("*")
@Validated
public class NsxController {
    @Autowired
    INsxService nsxService;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("/get-all-nsx")
    List<NsxDTO> findAllNsx() {
        List<Nsx> nsxList = nsxService.findAll();
        List<NsxDTO> nsxDTOS = nsxList.stream()
                .map(nsx -> mapper.map(nsx, NsxDTO.class))
                .collect(Collectors.toList());

        return nsxDTOS;
    }
    @PostMapping("/create-nsx")
    public ResponseEntity<?> createNsx(@RequestBody CreateNsx createNsx) throws Exception {
        nsxService.createNsx(createNsx);
        return ResponseEntity.ok("Thêm đơn hàng thành công");
    }
    @PutMapping("/update-nsx/{id}")
    public ResponseEntity<?> updateNsx(@PathVariable Integer id,@RequestBody UpdateNsx updateNsx) throws Exception {
        nsxService.updateNsx(id,updateNsx);
        return ResponseEntity.ok("Sửa đơn hàng thành công");
    }
    @DeleteMapping("/delete-nsx/{id}")
    public ResponseEntity<?> deleteNsx(@PathVariable("id") Integer id){
        nsxService.deleteByNsxId(id);
        return ResponseEntity.ok("Xóa đơn hàng thàng công");
    }
}
