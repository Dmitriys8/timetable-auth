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
public class Token {

    @JsonProperty("H-Hasura-user-id")
    private String XHasuraUserId;

    @JsonProperty("H-Hasura-user-role")
    private String XHasuraUserRole;

    @JsonProperty("H-Hasura-product-id")
    private String XHasuraProductId;

}
