package Database_Study.Database_Study.global.configuration.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    private SecretKey secretKey;

    public JwtUtil(@Value("${jwt.token.key}") String key) { // 암호화 구현
        this.secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    // 검증 진행 메소드들

    public String getUsername(String token) {
        // 암호화된 token 을 verifyWith()으로 검증 후 우리 서버가 갖고 있는 키와 맞는 지 확인 후, Claim 확인 후 real 데이터 갖고 오기.
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("username", String.class);
    }
    public String getRole(String token) {
        // 암호화된 token 을 verifyWith()으로 검증 후 우리 서버가 갖고 있는 키와 맞는 지 확인 후, Claim 확인 후 real 데이터 갖고 오기.
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }
    public boolean isExpired(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }
    
    // 검증 진행 메소드들 
    
    // 발급 메소드
    public String createToken(String username, String role, Long expireTime){

        return Jwts.builder()
                .claim("username", username)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(secretKey) // 발급 전 마지막 암호화
                .compact();
    }

}
