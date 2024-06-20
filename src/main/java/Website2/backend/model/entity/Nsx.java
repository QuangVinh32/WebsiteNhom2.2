package Website2.backend.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "`nsx`")
public class Nsx {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "nsx", cascade = CascadeType.ALL)
    private List<Product> products;

}
