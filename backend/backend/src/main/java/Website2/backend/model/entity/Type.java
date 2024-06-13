package Website2.backend.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "`type`")
@Entity
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private int typeId;

    @Column(name = "type_name",nullable = false, unique = true)
    private String typeName;

    @Column(name = "description")
    private String Description;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private List<Product> products;
}
