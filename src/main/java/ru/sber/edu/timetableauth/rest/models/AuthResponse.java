package ru.sber.edu.timetableauth.rest.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@AllArgsConstructor
@Setter
@Component
@NoArgsConstructor
public class AuthResponse {

    @JsonProperty("status")
    private String status;

    @JsonProperty("token")
    private String token;

}
