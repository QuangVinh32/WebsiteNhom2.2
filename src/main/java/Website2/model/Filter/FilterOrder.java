package Website2.model.Filter;

import lombok.Data;

import java.util.PrimitiveIterator;

@Data
public class FilterOrder {
    private String search;
    private Integer minOrderId;
    private Integer maxOrderId;
    private Integer minTotal;
    private Integer maxTotal;
    private Boolean  idAsc;
    private Boolean  idDesc;
    private Boolean  totalAsc;
    private Boolean totalDesc;
}
