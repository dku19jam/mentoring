package com.dku.mentoring.global.auth;

import com.dku.mentoring.user.entity.UserRole;
import com.dku.mentoring.user.service.UserAuthoritiesService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtProvider {

    public static final String AUTHORIZATION = "Authorization";

    @Value("${jwt.secret}")
    private String secretKey;
    private UserAuthoritiesService userAuthoritiesService;

    private Long jwtValidityTime = 8 * 60 * 60 * 1000L; //8시간
    private Long refreshValidityTime = 14 * 24 * 60 * 60 * 1000L; //2주

    //토큰 생성
    public String createAccessToken(String studentId, List<UserRole> roles) {
        Claims claims = Jwts.claims().setSubject(studentId);
        claims.put("roles", roles);
        Date now = new Date();
        Date validityTime = new Date(now.getTime() + jwtValidityTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validityTime)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    //권한정보 획득
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userAuthoritiesService.loadUserByUsername(this.getStudentId(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    //토큰에 있는 studentId 획득
    public String getStudentId(String token) {
        try {
            return (String) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        }catch (ExpiredJwtException e) {
            return e.getClaims().getSubject();
        } catch (SignatureException e) {
            throw new RuntimeException("Access Token이 올바르지 않습니다.");
        }
    }

    //Authorization Header를 통해 인증
    public String resolveToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null) {
            return null;
        }
        return header.replace("Bearer ", "");
    }

    //토큰 유효성 검사
    public boolean validateJwtToken(String token) throws JwtException{
        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token);
        return !claims.getBody().getExpiration().before(new Date());
    }

    public String createRefreshToken(String payload) {

        Claims claims = Jwts.claims().setSubject(payload);
        Date now = new Date();
        Date validityTime = new Date(now.getTime() + refreshValidityTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        String refreshTokenExpirationAt = simpleDateFormat.format(validityTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validityTime)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }


    public Long getTokenExpireTime(String accessToken) {

        Base64.Decoder decoder = Base64.getUrlDecoder();
        String[] parts = accessToken.split("\\.");
        ObjectMapper mapper = new ObjectMapper();
        String payload = new String(decoder.decode(parts[1]));
        Map exp = null;

        try {
            exp = mapper.readValue(payload, Map.class);
            return ((Number) exp.get("exp")).longValue();
        } catch (IOException err) {
            throw new RuntimeException(err);
        }
    }
}
