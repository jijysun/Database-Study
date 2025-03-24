package Database_Study.Database_Study.service;

import Database_Study.Database_Study.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    public void testService() {
        User user = User.builder()
                .name("test")
                .age(15)
                .email("test@test.com")
                .create_date(new Date(250228))
                .build();

        user.
    }
}