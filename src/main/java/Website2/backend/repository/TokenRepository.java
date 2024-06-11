package Website2.backend.repository;

import Website2.backend.model.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Token findByToken(String token);

    List<Token> findAllByExpirationIsAfter(Date exDate);
}
