package Website2.model.entity;

import javax.persistence.*;
import lombok.Data;

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
