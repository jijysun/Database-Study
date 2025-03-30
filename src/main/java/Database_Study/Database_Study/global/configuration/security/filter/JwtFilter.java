package Database_Study.Database_Study.global.configuration.security.filter;

import Database_Study.Database_Study.domain.user.dto.CustomUserDetails;
import Database_Study.Database_Study.domain.user.entity.User;
import Database_Study.Database_Study.global.configuration.security.jwt.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // request 에서 정보 추출하기
        String authorization = request.getHeader("Authorization");

        if (authorization.isEmpty() || !authorization.startsWith("Bearer ")) {
            log.info("JWT token does not begin with Bearer or null");
            filterChain.doFilter(request, response);

            return;
        }

        // 소멸 시간이 지났는지 확인할 것 -> JwtUtil 에 구현해두었던 메소드 사용
        String token = authorization.split(" ")[1];
        if (jwtUtil.isExpired(token)) {
            log.info("JWT token expired");
            filterChain.doFilter(request, response);
        }

        // 최종 확인 완료!
        // token ㅣ기반으로 일시적인 세션 생성 및 SecurityContextHolder에 일시 저장
        String username = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        // 토큰에는 비밀번호가 없음! 넣어주어야 하는데 DB 에서 조회 시 매번 요청이 올 때 마다 DB에 조회해야하는 안좋음 발생. COntextHolder에 정확한 거 안담아도 됨
        User user = User.builder()
                .username(username)
                .password("tempPassword")
                .role(role)
                .build();

        // 넣은 후
        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        // 스프링 시큐리티 인증 토큰 세션
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

        // 일시 저장
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
