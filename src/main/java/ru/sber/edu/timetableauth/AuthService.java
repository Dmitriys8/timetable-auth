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
        String rolesString = "";
        int counter = 1;
        for (String role : roles){
            rolesString = rolesString
                    .concat(role);
            if (counter < roles.size()){
                rolesString = rolesString
                        .concat(", ");
            }
            counter++;
        }
        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put("X-Hasura-User-Id", authInput.getProductUserID());
        tokenData.put("X-Hasura-Role", "user");
        tokenData.put("X-Hasura-Is-Owner", "false");
        tokenData.put("X-Hasura-Allowed-Roles", rolesString);
        JwtBuilder jwtBuilder = Jwts.builder();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 100);
        jwtBuilder.setExpiration(calendar.getTime());
        jwtBuilder.setClaims(tokenData);
        return jwtBuilder.signWith(SignatureAlgorithm.HS512, Constants.JWT_KEY).compact();
    }
}
