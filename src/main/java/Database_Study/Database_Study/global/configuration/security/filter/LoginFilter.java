package Database_Study.Database_Study.global.configuration.security.filter;

import Database_Study.Database_Study.domain.user.dto.CustomUserDetails;
import Database_Study.Database_Study.global.configuration.security.jwt.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@RequiredArgsConstructor
@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    /*
    *
    *
    * */

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtTokenUtil;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        
        // 요청에서 각 정보 추출
        String username = obtainUsername(request);
        String password = obtainPassword(request);

        log.info("username={} password={}", username, password);

        // token에 username, password 담아서 검증
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password, null);

        return authenticationManager.authenticate(authenticationToken); // 검증해주세요!!!
    }

    @Override // 검증, 로그인 성공 시 호출될 메소드
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        CustomUserDetails customUserDetails =  (CustomUserDetails) authResult.getPrincipal();
        // JWT 에 담을 정보 가지고 오기

        String username = customUserDetails.getUsername();

        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority next = iterator.next();
        String role = next.getAuthority();

        // JWT 발급!
        String newToken = jwtTokenUtil.createToken(username, role, 60*60*10L);
        response.setHeader("Authorization", "Bearer " + newToken);
    }

    @Override // 검증, 로그인 실패 시 호출될 메소드
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
