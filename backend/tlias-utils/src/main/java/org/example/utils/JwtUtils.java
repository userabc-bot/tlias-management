package org.example.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    // 密钥（Base64编码的"itheima"）
    private static final String SECRET_KEY = "aXRlaGltYQ==";
    
    // 过期时间：2小时（单位：毫秒）
    private static final long EXPIRATION_TIME = 2 * 60 * 60 * 1000;

    /**
     * 生成JWT令牌
     * @param claims 自定义声明（载荷）
     * @return 生成的JWT令牌
     */
    public static String generateToken(Map<String, Object> claims) {
        // 计算过期时间（当前时间 + 过期时长）
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        
        return Jwts.builder()
                .setClaims(claims) // 设置自定义声明
                .setExpiration(expirationDate) // 设置过期时间
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 设置签名算法和密钥
                .compact(); // 生成令牌
    }

    /**
     * 解析JWT令牌，获取声明（载荷）
     * @param token JWT令牌
     * @return 解析后的声明
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY) // 设置签名密钥（需与生成时一致）
                .parseClaimsJws(token) // 解析令牌
                .getBody(); // 获取载荷部分
    }
}
