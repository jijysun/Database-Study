package Database_Study.Database_Study.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder @ToString
@Entity @AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date create_date;

    private String name;

    private int age;

    private String email;
}
