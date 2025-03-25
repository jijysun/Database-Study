package Database_Study.Database_Study.domain.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

    @Email @NotBlank
    private String email;

    @Enumerated(EnumType.STRING)
    private Grade grade;

}
