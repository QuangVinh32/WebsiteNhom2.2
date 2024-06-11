package Website2.backend.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "reviews")
@Entity

public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int productId;
    private int accountId;
    private String content;
    private int rate;
}
