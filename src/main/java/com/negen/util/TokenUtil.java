package com.negen.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ Author     ：Negen
 * @ Date       ：Created in 20:49 2020/3/5
 * @ Description：token生成、校验工具类
 * @ Modified By：
 * @Version: 1.0
 */
public class TokenUtil {
    //密钥
    public static final String SECRET = "asdfasdfafqwes2312`sdf;";
    //过期时间:秒
    public static final int EXPIRE = 5;

    /**
     * 生成Token
     * @param userId
     * @param userName
     * @return
     * @throws Exception
     */
    public static String createToken(String userId, String userName) throws Exception {
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.SECOND, EXPIRE);
        Date expireDate = nowTime.getTime();

        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        String token = JWT.create()
                .withHeader(map)
                .withClaim("userId", userId)
                .withClaim("userName", userName)
                .withSubject("token")
                .withIssuedAt(new Date())
                .withExpiresAt(expireDate)
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    /**
     * 验证Token
     * @param token
     * @return
     * @throws Exception
     */
    public static Map<String, Claim> verifyToken(String token)throws Exception{
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);
        }catch (Exception e){
            throw new RuntimeException("凭证已过期，请重新登录");
        }
        return jwt.getClaims();
    }

    /**
     * 解析Token
     * @param token
     * @return
     */
    public static Map<String, Claim> parseToken(String token){
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaims();
    }


    public static void main(String[] args) throws Exception {
        String token = TokenUtil.createToken("111", "admin");
        System.out.println(token);
//		System.out.println(token.length());
        Map<String, Claim> tokenResult = TokenUtil.verifyToken(token);
        System.out.println(tokenResult.get("userId").asString());
        System.out.println(tokenResult.get("userName").asString());
    }
}
