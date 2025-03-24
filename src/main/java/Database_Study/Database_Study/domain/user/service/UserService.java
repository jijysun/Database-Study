package Database_Study.Database_Study.domain.user.service;

import Database_Study.Database_Study.domain.user.Repository.UserRepository;
import Database_Study.Database_Study.domain.user.dto.SignUpDto;
import Database_Study.Database_Study.domain.user.entity.Grade;
import Database_Study.Database_Study.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service @RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void signUpService(SignUpDto signUpDto) {

        User user = User.builder()
                .userId(signUpDto.getId())
                .create_date(new Date())
                .password(bCryptPasswordEncoder.encode(signUpDto.getPassword()))
                .grade(Grade.USER)
                .build();

        userRepository.save(user);
    }
}