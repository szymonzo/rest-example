package com.example.entity.db;

import com.example.entity.db.Token;

import javax.persistence.*;

/**
 * Created by szymon on 21.04.16.
 */

@Entity
public class User {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    private String login;

    @Column
    private String name;

    @Column
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private Token token;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
