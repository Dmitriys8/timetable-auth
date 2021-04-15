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
public class Request {

    @JsonProperty("variables")
    private Map<String, String> variables;

    @JsonProperty("operationName")
    private String operationName;

    @JsonProperty("query")
    private String query;
}
