package Database_Study.Database_Study.domain.user.service;

import Database_Study.Database_Study.domain.user.Repository.UserRepository;
import Database_Study.Database_Study.domain.user.dto.SignUpDto;
import Database_Study.Database_Study.domain.user.entity.Role;
import Database_Study.Database_Study.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Slf4j
@Service @RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void signUpService(SignUpDto signUpDto) {

        Optional<User> userByEmail = userRepository.findUserByEmail(signUpDto.getEmail());
        if (userByEmail.isPresent()) {
           log.warn("User with email {} already exists", signUpDto.getEmail());
           return;
        }

        User user = User.builder()
//                .userId(signUpDto.getId())
                .email(signUpDto.getEmail())
                .create_date(new Date())
                .password(bCryptPasswordEncoder.encode(signUpDto.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
    }
}