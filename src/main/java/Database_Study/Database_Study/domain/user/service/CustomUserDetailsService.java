package Database_Study.Database_Study.domain.user.service;

import Database_Study.Database_Study.domain.user.Repository.UserRepository;
import Database_Study.Database_Study.domain.user.dto.CustomUserDetails;
import Database_Study.Database_Study.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service @RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {


    /* UserDetailService 구현체.
    * -
    * */

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 애초에 클라이언트 요청은 아이딩 & 비밀번호 -> 찾는 건 email로 해야 됨!
        Optional<User> userByUsername = userRepository.findUserByEmail(username);

        if (userByUsername.isPresent()) {
            return new CustomUserDetails(userByUsername.get());
        }

        return null;
    }
}
