package ru.sber.edu.timetableauth.rest.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Roles {

    @JsonProperty("user_roles")
    private List<Map<String, String>> rolesList;
}
