package com.novatechzone.web.util;

import com.novatechzone.web.Exception.AccessDeniedException;
import com.novatechzone.web.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private static final String SECRET = "JBX/b]MJ)x0M.}Ve6i(b+[pt,f(T53A8u+Z8Mwx/;UQc@V@&RFm%ubutt)4;x]7X";
    private static final long EXPIRY_DURATION = 60 * 30;

    public String generateAccessToken(User user) {

        long currentTimeMillis = System.currentTimeMillis();
        long expiryTime = currentTimeMillis + EXPIRY_DURATION * 1000;

        Date issuedAt = new Date(currentTimeMillis);
        Date expiryAt = new Date(expiryTime);

        Claims claims = Jwts.claims().setIssuer(user.getId().toString()).setIssuedAt(issuedAt).setExpiration(expiryAt);
        claims.put("name", user.getName());
        claims.put("email", user.getEmail());
        claims.put("user_role", user.getUserRole());

        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

    public Claims verifyToken(String authorization) {
        try {
            return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(authorization).getBody();
        } catch (Exception e) {
            throw new AccessDeniedException("Access Denied");
        }
    }
}
