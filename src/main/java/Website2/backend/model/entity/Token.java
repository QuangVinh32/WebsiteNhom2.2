package Website2.backend.model.entity;



import javax.persistence.*;
import lombok.Data;
<<<<<<< HEAD:src/main/java/Website2/backend/model/entity/Token.java


import javax.persistence.*;
=======
import lombok.NoArgsConstructor;

>>>>>>> 0a285c5cfca88a488c352faa5d63af54bf6abb57:backend/backend/src/main/java/Website2/backend/model/entity/Token.java
import java.util.Date;

@Data
@Entity
@Table(name = "token")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "token", length = 1000)
    private String token;

    @Column(name = "user_Agent")
    private String userAgent;

    @Column(name = "expiration")
    private Date expiration;
}
