package Website2.backend.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

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

