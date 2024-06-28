package Website2.controller;


import Website2.model.DTO.TypeDTO;
import Website2.model.entity.Type;
import Website2.model.request.CreateType;
import Website2.model.request.UpdateType;
import Website2.service.ITypeService;
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
public class TypeController {
    @Autowired
    private ITypeService typeService;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("/get-all-type")
    List<TypeDTO> findAllType() {
        List<Type> types = typeService.findAll();
        List<TypeDTO> typeDTOS = types.stream()
                .map(type -> mapper.map(type, TypeDTO.class))
                .collect(Collectors.toList());

        return typeDTOS;
    }
    @PostMapping("/create-type")
    public ResponseEntity<?> createType(@RequestBody CreateType createType) throws Exception {
        typeService.createType(createType);
        return ResponseEntity.ok("Thêm loại thành công");
    }
    @PutMapping("/update-typer/{id}")
    public ResponseEntity<?> updateType(@PathVariable Integer id,@RequestBody UpdateType updateType) throws Exception {
        typeService.updateType(id,updateType);
        return ResponseEntity.ok("Sửa loại thành công");
    }
    @DeleteMapping("/delete-type/{id}")
    public ResponseEntity<?> deleteType(@PathVariable("id") Integer id){
        typeService.deleteByTypeId(id);
        return ResponseEntity.ok("Xóa loại thàng công");
    }
}
