package com.example.entity.api;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by szymon on 21.04.16.
 */
public class StandardAuthenticationIn extends AbstractAuthenticationIn {

    private String type = "STANDARD";

    @NotNull
    @NotBlank
    private String login;

    @NotBlank
    @NotNull
    private String password;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
