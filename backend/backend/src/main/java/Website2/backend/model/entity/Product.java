package Website2.backend.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "product")
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
    @Column(name = "price")
    private int price;
    @Column(name = "discount")
    private int discount;
    @Column(name = "image")
    private String image;
    @Column(name = "status")
    private productStatus status;
    @Column(name = "lo_luong_ton_kho")
    private int soLuongTonKho;

    
}
