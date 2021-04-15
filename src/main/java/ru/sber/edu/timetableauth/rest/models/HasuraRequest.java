package ru.sber.edu.timetableauth.rest.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HasuraRequest {

    @JsonProperty("headers")
    private Map<String, String> headers;

    @JsonProperty("request")
    private Request request;
}
