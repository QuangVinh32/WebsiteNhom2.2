package Website2.backend.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "`product`")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private int productId; // Changed type to Long for compatibility with IDENTITY strategy

    @Column(name = "productCode", nullable = false, unique = true)
    private int productCode;

    @Column(name = "productName", nullable = false)
    private String productName;

    @Column(name = "productDescription")
    private String descriptionProduct;

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
    private LocalDateTime createdTime;

    @Column(name = "soLuongTonKho", nullable = false)
    private int soLuongTonKho;

    // Đúng
    @ManyToOne
    @JoinColumn(name = "nsxId")
    private Nsx nsx;

    // Đúng
    @ManyToOne
    @JoinColumn(name = "typeId")
    private Type type;

    // Đúng
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Reviews> reviews;

    // Đúng
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetail;

    // Đúng
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<CartDetail> cartDetails;
}
