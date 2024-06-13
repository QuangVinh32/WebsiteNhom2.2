package Website2.backend.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "`product`")
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_code",nullable = false,unique = true)
    private int productCode;

    @Column(name = "product_name",nullable = false)
    private String nameProduct;

    @Column(name = "product_description")
    private String descriptionProduct;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "discount")
    private int discount;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "status", nullable = false)
    private productStatus status;

    @Column(name = "create_time", nullable = false, updatable = false)
    private LocalDateTime createdTime;

    @Column(name = "so_luong_ton_kho", nullable = false)
    private int soLuongTonKho;

    @ManyToOne
    @JoinColumn(name = "nsx_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Nsx nsx;

    @ManyToOne
    @JoinColumn(name = "type_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Type type;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Reviews> reviews;

    @ManyToOne
    @JoinColumn(name = "order_detail_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private OrderDetail orderDetail;

    @ManyToOne
    @JoinColumn(name = "cart_detail_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CartDetail cartDetail;
}
