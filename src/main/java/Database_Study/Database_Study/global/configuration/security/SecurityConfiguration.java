package Database_Study.Database_Study.global.configuration.security;


import Database_Study.Database_Study.global.configuration.security.filter.JwtFilter;
import Database_Study.Database_Study.global.configuration.security.filter.LoginFilter;
import Database_Study.Database_Study.global.configuration.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtUtil jwtTokenUtil;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/", "/login", "/loginProcess", "/register").permitAll() // 메인 및 로그인 페이지는 접근 허용
                .requestMatchers("/admin").hasRole("ADMIN") // ADMIN 권한을 갖는 사용자만 /admin 접근 허용
                .requestMatchers("/my/**", "/test").hasAnyRole("ADMIN", "USER") // 이 중에서 1개 이상의 권한을 갖으면 OK
                .anyRequest().authenticated() // 모든 req 에 인증된 사용자만 접근 허용
        );

        http.formLogin((auth) -> auth.loginPage("/login")
                .loginProcessingUrl("/loginProcess")
                .permitAll()
        );

        http.csrf((auth) -> auth.disable());

        http.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        http.addFilterAt(new JwtFilter(jwtTokenUtil), LoginFilter.class);

        // 구현 필터 등록, 설정 정보도 넘겨줌
        // AuthenticationManager 와 JwtUtil을 넘기기
        http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtTokenUtil), UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
