package Website2.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "`type`")
@Entity
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "typeId")
    private int typeId;

    @Column(name = "typeName",nullable = false, unique = true)
    private String typeName;

    @Column(name = "description")
    private String Description;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private List<Product> products;
}
