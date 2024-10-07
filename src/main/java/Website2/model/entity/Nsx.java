package Website2.model.entity;
import javax.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "`nsx`")
public class Nsx {
    @Id
    @Column(name = "nsxId")
    private Integer nsxId;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private NsxRole nsxRole;

    @OneToMany(mappedBy = "nsx", cascade = CascadeType.ALL)
    private List<Product> products;

}
