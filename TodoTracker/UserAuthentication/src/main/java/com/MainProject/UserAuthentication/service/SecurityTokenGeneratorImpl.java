package com.MainProject.UserAuthentication.service;

import com.MainProject.UserAuthentication.domain.Users;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SecurityTokenGeneratorImpl implements SecurityTokenGenerator{
    @Override
    public Map<String, String> tokenGenerator(Users user) {
        String jwtToken = null;
        Map<String,String> getinfo=new HashMap<>();
        user.setPassword("");
        Map<String,Object> setdata=new HashMap<>();
        setdata.put("give_role",user.getRole());
        setdata.put("give_verify",user.getVerify());
        setdata.put("user_email",user.getEmail());
        jwtToken = Jwts.builder().setClaims(setdata)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "code")
                .compact();
        Map<String, String> map = new HashMap<>();
        map.put("token", jwtToken);
        map.put("message", "user logged in successfully");
        map.put("verify",user.getVerify());
        map.put("role",user.getRole());
        return map;
    }
}
