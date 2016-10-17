package com.example.entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serializable;

/**
 * Created by kevin on 15/10/2016.
 */
@Entity
@DynamicUpdate
@SelectBeforeUpdate
@DynamicInsert
public class Token extends AbstractEntity implements Serializable {

    private String token;

    @OneToOne(cascade={CascadeType.ALL})
    private User user;

    public Token() {
        super();
    }

    public Token(String token) {
        super();
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
