package Database_Study.Database_Study.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Entity @AllArgsConstructor @NoArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date create_date;

    @Column(unique = true, nullable = false)
    private String userId;
    private String password;

    @Enumerated(EnumType.STRING)
    private Grade grade;

}
