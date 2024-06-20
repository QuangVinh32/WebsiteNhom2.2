package Website2.backend.model.entity;
<<<<<<< HEAD:src/main/java/Website2/backend/model/entity/Order.java
=======

import jakarta.persistence.*;
import lombok.Data;
>>>>>>> 0a285c5cfca88a488c352faa5d63af54bf6abb57:backend/backend/src/main/java/Website2/backend/model/entity/Order.java

import javax.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "Orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private int orderId;

    @Column(name = "total")
    private int total;

    @Column(name = "address")
    private String address;
<<<<<<< HEAD:src/main/java/Website2/backend/model/entity/Order.java

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "sale_date")
=======
    @Column(name = "orderDate")
    private Date orderDate;
    @Column(name = "saleDate")
>>>>>>> 0a285c5cfca88a488c352faa5d63af54bf6abb57:backend/backend/src/main/java/Website2/backend/model/entity/Order.java
    private Date saleDate;

    @Column(name = "status")
<<<<<<< HEAD:src/main/java/Website2/backend/model/entity/Order.java
    @Enumerated(EnumType.STRING) // Sử dụng EnumType.STRING để lưu trữ giá trị của Enum dưới dạng chuỗi
    private OrderStatus status;

    @Column(name = "note")
    private String note;
=======
    private OrderStatus status;
    @Column(name = "note")
    private String note;

    // Đúng
    @ManyToOne
    @JoinColumn(name = "userId")
    private Users users;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;
>>>>>>> 0a285c5cfca88a488c352faa5d63af54bf6abb57:backend/backend/src/main/java/Website2/backend/model/entity/Order.java
}
