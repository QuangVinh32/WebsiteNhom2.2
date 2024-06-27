package Website2.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "type")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "typeId")
    private Long typeId;

    @Column(name = "typeName", nullable = false, unique = true)
    private String typeName;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private List<Product> products;
}
