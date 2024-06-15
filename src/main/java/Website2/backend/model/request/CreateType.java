package Website2.backend.model.request;

import lombok.Data;

@Data
public class CreateType {
    private Integer typeId;
    private String typeName;
    private String Description;
}
