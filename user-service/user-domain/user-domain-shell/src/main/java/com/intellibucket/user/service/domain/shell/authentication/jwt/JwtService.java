//package com.intellibucket.user.service.domain.shell.authentication.jwt;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import lombok.Getter;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import javax.crypto.SecretKey;
//import java.security.Key;
//import java.util.*;
//
//@Service
//@Getter
//public class JwtService {
//    @Value("${issue.key}")
//    private String SECRET;
//
//    @Value("${security.jwt.expiration-time}")
//    private long jwtExpirationInMs;
//
//    public String generateToken(UUID userId, List<String> roles) {
//        System.out.println("generateToken");
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("roles", roles); // Store roles in a list
//        System.out.println("claims: " + claims);
//        return Jwts.builder()
//                .claims(claims)
//                .subject(userId.toString())
//                .issuedAt(new Date(System.currentTimeMillis()))
//                .expiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
//                .signWith(getSignInKey())
//                .compact();
//    }
//
//    public Claims getClaimsFromToken(String token) {
//        return Jwts
//                .parser()
//                .verifyWith((SecretKey)getSignInKey())
//                .build()
//                .parseSignedClaims(token)
//                .getPayload();
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            getClaimsFromToken(token);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public String getRoleFromToken(String token) {
//        Claims claims = getClaimsFromToken(token);
//        return claims.get("role", String.class);
//    }
//
////    public String generateToken(UserDetails userDetails) {
////        return generateToken(new HashMap<>(), userDetails);
////    }
////    public String generateToken(String userId) {
////        Date now = new Date();
////        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
////
////        // Build and sign the token with userId as the subject
////        return Jwts.builder()
////                .setSubject(userId)
////                .setIssuedAt(new Date())
////                .setExpiration(expiryDate)
////                .signWith(SignatureAlgorithm.HS512, jwtSecret)
////                .compact();
////    }
//
////    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
////        return buildToken(extraClaims, userDetails, jwtExpiration);
////    }
////
////    private String buildToken(
////            Map<String, Object> extraClaims,
////            UserDetails userDetails,
////            long expiration
////    ) {
////        return Jwts
////                .builder()
////                .setClaims(extraClaims)
////                .setSubject(userDetails.getUsername())
////                .setIssuedAt(new Date(System.currentTimeMillis()))
////                .setExpiration(new Date(System.currentTimeMillis() + expiration))
////                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
////                .compact();
////    }
//
////    public String generateToken(String email) {
////        Map<String, Object> claims = new HashMap<>();
////        return createToken(claims, email);
////    }
//
////    private String extractEmailFromToken(String token) {
////        Claims claims = Jwts
////                .parserBuilder()
////                .setSigningKey(getSignInKey())
////                .build()
////                .parseClaimsJws(token)
////                .getBody();
////        return claims.getSubject();
////    }
//
////    public Boolean validateToken(String token, UserDetails userDetails) {
////        String username = extractUser(token);
////        Date expirationDate = extractExpiration(token);
////        return userDetails.getUsername().equals(username) && !expirationDate.before(new Date());
////    }
//
//    private Date extractExpiration(String token) {
//        Claims claims = Jwts
//                .parser()
//                .verifyWith((SecretKey)getSignInKey())
//                .build()
//                .parseSignedClaims(token)
//                .getPayload();
//        return claims.getExpiration();
//    }
//
////    public String extractUser(String token) {
////        Claims claims = Jwts
////                .parserBuilder()
////                .setSigningKey(getSignInKey())
////                .build()
////                .parseClaimsJws(token)
////                .getBody();
////        return claims.getSubject();
////    }
//
////    private String createToken(Map<String, Object> claims, String userName) {
////        return Jwts.builder()
////                .setClaims(claims)
////                .setSubject(userName)
////                .setIssuedAt(new Date(System.currentTimeMillis()))
////                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 1000))
////                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
////                .compact();
////    }
//
//    private Key getSignInKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//}