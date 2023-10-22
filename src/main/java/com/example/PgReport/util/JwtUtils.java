package com.example.PgReport.util;

import com.example.PgReport.User.UserBO;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@Slf4j
public class JwtUtils {
    private static final String SECRET_KEY="dsssf423ddqe";

    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims,userDetails.getUsername());
    }

    public boolean validateToken(String token, UserDetails userDetails){
       String userName = extractUserName(token);
        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private String createToken(Map<String,Object> claims,String subject){
        Date now = new Date(System.currentTimeMillis());
        Date until = new Date(System.currentTimeMillis() +1000*60*60*10);
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(now).setExpiration(until)
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY).compact();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }


    public  <T> T extractClaim(String token, Function<Claims,T> claimResolver){
        final Claims claims = extractAllClaims(token);
        return  claimResolver.apply(claims);
    }

    public  Claims extractAllClaims(String token){

        return  Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public String extractUserName(String token){
        return  extractClaim(token,Claims::getSubject);
    }

    private Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }
    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
}
