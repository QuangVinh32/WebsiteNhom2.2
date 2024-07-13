package Website2.model.request;

import lombok.Data;

@Data
public class FilterProduct {
    private String search;
    private Integer priceMin;
    private Integer priceMax;
    private Boolean  nameAsc;
    private Boolean  nameDesc;

}
