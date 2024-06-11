package Website2.backend.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "nsx")
public class Nsx {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "description")
    private String description;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
