package com.netcracker.savenko.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LogInParam {
    @NotEmpty(message = "Username is required")
    @Min(5)
    @Max(25)
    private String username;

    @NotEmpty(message = "Password is required")
    @Min(5)
    @Max(25)
    @Pattern(regexp = "^[0-9a-zA-Z]+$", message = "Password is invalid")
    private String password;

    public LogInParam() {
    }

    public LogInParam(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
