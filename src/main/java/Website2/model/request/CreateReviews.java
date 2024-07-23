package Website2.model.request;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateReviews {
    private int userId;
    private int productId;
    private String content;
    private int rate;
    private LocalDateTime time;

}
