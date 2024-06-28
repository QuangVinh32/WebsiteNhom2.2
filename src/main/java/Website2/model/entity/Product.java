package Website2.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private Long productId;

    @Column(name = "productCode", nullable = false, unique = true)
    private String productCode;

    @Column(name = "productName", nullable = false)
    private String productName;

    @Column(name = "productDescription")
    private String productDescription;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "discount")
    private int discount;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Column(name = "createTime", nullable = false, updatable = false)
    private LocalDateTime createTime;

    @Column(name = "soLuongTonKho", nullable = false)
    private int soLuongTonKho;

    @ManyToOne
    @JoinColumn(name = "nsxId")
    private Nsx nsx;

    @ManyToOne
    @JoinColumn(name = "typeId")
    private Type type;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Reviews> reviews;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetail;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<CartDetail> cartDetails;
}
