package org.example;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;

public class JwtTest {

    @Test
    public void testGenJwt(){
        Map<String, Object> claims=new HashMap<>();
        claims.put("id",10);
        claims.put("username","itheima");

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "aXRlaGltYQ==")//指定加密散发，密钥
                .addClaims(claims)//添加自定义信息
                .setExpiration(new Date(System.currentTimeMillis() + 60*1000 ))//设置过期时间
                .compact();//生成令牌

        System.out.println(jwt);
    }


    @Test
    public void testParseJwt(){
        Claims claims = Jwts.parser().setSigningKey("aXRlaGltYQ==")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MTAsInVzZXJuYW1lIjoiaXRoZWltYSIsImV4cCI6MTc1NjY0OTkwN30.QopuN-NjIuzQw2SZsQZu7Pq8KfYifnx0O5C0-U0g5c4")
                .getBody();

        System.out.println(claims);
    }


}




















