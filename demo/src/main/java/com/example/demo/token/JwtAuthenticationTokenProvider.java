package com.example.demo.token;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtAuthenticationTokenProvider implements AuthenticationTokenProvider {
//    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenProvider.class);
 
    // 보통 설정파일에 관리하고 `@Value` 등으로 주입받아 사용하는 것을 추천
    private static final String SECRET_KEY = "SOME_SECRET_KEY";
    private static final long EXPIRATION_MS = 1000 * 60 * 60 * 24; 
 
    @Override
    public String parseTokenString(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
 
    @Override
    public AuthenticationToken issue(Long userNo) {

        return JwtAuthenticationToken.builder().token(buildToken(userNo)).build();
        
    }
 
    // JWT 토큰 생성
    private String buildToken(Long userNo) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiredAt = now.plus(EXPIRATION_MS, ChronoUnit.MILLIS);
        return Jwts.builder()
                .setSubject(String.valueOf(userNo))
                .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(expiredAt.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
 
    @Override
    public Long getTokenOwnerNo(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }
 
    @Override
    public boolean validateToken(String token) {
//        if (StringUtils.isNotEmpty(token)) {
    	if (token == null || token == "") {
            try {
                Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
                return true;
            } catch (SignatureException e) {
//				System.out.println("Invalid JWT signature", e);
            } catch (MalformedJwtException e) {
//                logger.error("Invalid JWT token", e);
            } catch (ExpiredJwtException e) {
//                logger.error("Expired JWT token", e);
            } catch (UnsupportedJwtException e) {
//                logger.error("Unsupported JWT token", e);
            } catch (IllegalArgumentException e) {
//                logger.error("JWT claims string is empty.", e);
            }
        }
        return false;
    }
}