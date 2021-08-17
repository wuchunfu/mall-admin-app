package com.mall.weapp.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * JWT工具类
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt.auth")
public class JwtUtil {

    private String secret;
    private Integer expire;
    /**
     * 生成token
     */
    public String generateToken(String openId){

        Calendar nowTime = Calendar.getInstance();
        Calendar expireTime = Calendar.getInstance();
        expireTime.add(Calendar.SECOND, expire);

        return Jwts.builder()
                .setHeaderParam("typ","JWt")
                .setSubject(openId)
                .setIssuedAt(nowTime.getTime())
                .setExpiration(expireTime.getTime())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 验证token
     */
    public Claims verifyToken(String token){
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 判断token是否过期
     */
    public boolean isTokenExpired(Claims claims){
        return claims.getExpiration().before(new Date());
    }

}
