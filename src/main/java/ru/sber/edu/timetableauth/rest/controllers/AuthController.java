package ru.sber.edu.timetableauth.rest.controllers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sber.edu.timetableauth.AuthService;
import ru.sber.edu.timetableauth.Constants;
import ru.sber.edu.timetableauth.rest.exceptions.HttpUnauthorizedException;
import ru.sber.edu.timetableauth.rest.models.AuthInput;
import ru.sber.edu.timetableauth.rest.models.HasuraRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    HttpServletRequest request;

    @Autowired
    AuthService authService;

    @PostMapping
    public String auth(
            @RequestBody(required = false) AuthInput requestBody,
            HttpServletResponse response
    ) {
        String token = authService.auth(requestBody);
        response.addHeader("timetableToken", token);
        return "success";
    }

    @PostMapping("/check")
    public Map<String, String> check(
            @RequestBody(required = false) HasuraRequest requestBody,
            HttpServletResponse response
    ) {
        try {
            String token = requestBody.getHeaders().get("timetableToken");
            Claims claims = Jwts.parser().setSigningKey(Constants.JWT_KEY).parseClaimsJws(token).getBody();
            Map<String, String> responseBody = new HashMap<>();
            for (Map.Entry<String, Object> entry : claims.entrySet()){
                responseBody.put(entry.getKey(), entry.getValue().toString());
            }
            return responseBody;
        } catch (Exception e){
            throw new HttpUnauthorizedException();
        }
    }
}
