package mk.ukim.finki.emt2025.model.domain;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class TokenLog {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String token;
    Date issuedAt;
    Date expiresAt;

    @ManyToOne
    User user;

    public TokenLog(String token, Date issuedAt, Date expiresAt, User user) {
        this.token = token;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
        this.user = user;
    }

    public TokenLog() {
    }

    public String getToken() {
        return token;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public User getUser() {
        return user;
    }

}
