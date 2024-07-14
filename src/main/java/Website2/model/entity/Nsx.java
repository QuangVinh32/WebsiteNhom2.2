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
    private Integer id;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "nsx", cascade = CascadeType.ALL)
    private List<Product> products;
//  Type = asus,lenovo,hp,dell,acer,macbook

}
