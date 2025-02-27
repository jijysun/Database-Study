package Database_Study.Database_Study.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @Entity @NoArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date create_date;

    private String name;

    private int age;

    private String email;

    @Builder
    public User (Date create_date, String name, int age, String email) {
        this.create_date = create_date;
        this.name = name;
        this.age = age;
        this.email = email;
    }

}
