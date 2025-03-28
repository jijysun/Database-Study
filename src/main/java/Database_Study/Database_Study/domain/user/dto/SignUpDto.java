package Database_Study.Database_Study.domain.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignUpDto {
    private String id;
    private String username;
    private String password;
    private String email;
}
