package ru.sber.edu.timetableauth;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sber.edu.timetableauth.communicators.AuthCommunicator;
import ru.sber.edu.timetableauth.rest.exceptions.HttpUnauthorizedException;
import ru.sber.edu.timetableauth.rest.models.AuthInput;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AuthService {


    @Autowired
    AuthCommunicator authCommunicator;

    public String auth(AuthInput authInput){
        List<String> roles = authCommunicator.auth(authInput);
        if (roles == null){
            throw new HttpUnauthorizedException();
        }
        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put("X-Hasura-allowed-roles", roles);
        tokenData.put("X-Hasura-user-id", authInput.getProductUserID());
        JwtBuilder jwtBuilder = Jwts.builder();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 100);
        jwtBuilder.setExpiration(calendar.getTime());
        jwtBuilder.setClaims(tokenData);
        String token = jwtBuilder.signWith(SignatureAlgorithm.HS512, Constants.JWT_KEY).compact();
        return token;
    }
}
