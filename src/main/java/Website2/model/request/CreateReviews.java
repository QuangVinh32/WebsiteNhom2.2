package Website2.model.request;


import lombok.Data;

@Data
public class CreateReviews {

    private int userId;

    private int productId;

    private String content;

    private int rate;

}
