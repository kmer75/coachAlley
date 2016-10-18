package com.example.entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by kevin on 15/10/2016.
 */
@Entity
@DynamicUpdate
@SelectBeforeUpdate
@DynamicInsert
public class Token extends AbstractEntity implements Serializable {

    static final long ONE_MINUTE_IN_MILLIS=60000;//millisecs

    private String token;

    private String type;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expired;

    @OneToOne(cascade={CascadeType.ALL})
    private User user;

    public Token() {
        super();
    }

    public Token(String token) {
        super();
        this.token = token;
    }

    public Token(String token, String type) {
        super();
        this.token = token;
        this.type = type;
    }

    public Token(String token, String type, User user) {
        super();
        this.token = token;
        this.type = type;
        this.user = user;
    }

    @PrePersist
    protected void prePersistExpiredTime() {
        setExpired(new Date(new Date().getTime() + ONE_MINUTE_IN_MILLIS * 30));
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
