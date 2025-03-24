package Database_Study.Database_Study.global.configuration.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/", "/login", "/loginProcess").permitAll() // 메인 및 로그인 페이지는 접근 허용
                .requestMatchers("/admin").hasRole("ADMIN") // ADMIN 권한을 갖는 사용자만 /admin 접근 허용
                .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER") // 이 중에서 1개 이상의 권한을 갖으면 OK
                .anyRequest().authenticated() // 모든 req 에 인증된 사용자만 접근 허용
        );

        http.formLogin((auth) -> auth.loginPage("/login")
                .loginProcessingUrl("/loginProcess")
                .permitAll()
        );

        http.csrf((auth) -> auth.disable());

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
