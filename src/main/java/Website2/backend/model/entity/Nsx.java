package Website2.backend.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "`nsx`")
public class Nsx {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "description")
    private String description;


    // Đúng
    @OneToMany(mappedBy = "nsx", cascade = CascadeType.ALL)
    private List<Product> products;

}