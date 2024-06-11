package Website2.backend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "nxs")
public class Nxs {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "description")
    private String description;
}
