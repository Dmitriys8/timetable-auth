package ru.sber.edu.timetableauth.communicators;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.sber.edu.timetableauth.Constants;
import ru.sber.edu.timetableauth.rest.models.AuthInput;
import ru.sber.edu.timetableauth.rest.models.Roles;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Exchanger;
import java.util.stream.Collectors;

@Component
public class AuthCommunicator {

    private static final String REQUEST_URI = Constants.HASURA_API_PATH + Constants.GET_USER_ROLES_PATH;

    private HttpHeaders httpHeaders;
    private RestTemplate restTemplate;

    public AuthCommunicator(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<String> auth(AuthInput authInput){
        HttpEntity entity = new HttpEntity(configureHeaders());
        Roles response = restTemplate.exchange(
                REQUEST_URI + "?productSecret=" + authInput.getProductSecret() + "&userId=" + authInput.getProductUserID(),
                HttpMethod.GET,
                entity,
                Roles.class
        ).getBody();
        if (response.getRolesList() != null && !response.getRolesList().isEmpty()){
            return response.getRolesList()
                    .stream()
                    .map(role -> role.get("role_id"))
                    .collect(Collectors.toList());
        } else {
            return null;
        }
    }

    private HttpHeaders configureHeaders(){
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setConnection("keep-alive");
        httpHeaders.add("X-Hasura-admin-secret", "L5ZalprIfFwo7CzSVyRiv9nTO40lD3KGm6q46iqJxnpVkeoeW4HIo03RJk1uwDRH");
        return httpHeaders;
    }
}
