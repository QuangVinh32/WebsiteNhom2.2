package Website2.backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity
@Table(name = "`users`")
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "username", nullable = false, unique = true, updatable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 8)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Reviews> reviews;

    public enum Role {
        ADMIN, MANAGER, USER, USER_NS
    }
}
