package Website2.model.request;

import lombok.Data;

import java.util.PrimitiveIterator;

@Data
public class FilterOrder {
    private String search;
    private Integer minId;
    private Integer maxId;
    private Integer minTotal;
    private Integer maxTotal;
}
