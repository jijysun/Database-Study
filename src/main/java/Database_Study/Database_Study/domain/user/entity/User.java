package Database_Study.Database_Study.domain.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@Builder @Getter
@Entity @AllArgsConstructor @NoArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date create_date;

    private String email;

    private String password;

    private String username;

    @Enumerated(EnumType.STRING)
    private Role role;

}
