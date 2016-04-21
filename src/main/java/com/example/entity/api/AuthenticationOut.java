package com.example.entity.api;

/**
 * Created by szymon on 21.04.16.
 */
public class AuthenticationOut {

    private String token;

    private Long id;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AuthenticationOut{");
        sb.append("token='").append(token).append('\'');
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
