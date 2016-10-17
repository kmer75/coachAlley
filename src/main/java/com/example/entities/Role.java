package com.example.entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by kevin on 12/10/2016.
 */
@Entity
@DynamicUpdate
@SelectBeforeUpdate
@DynamicInsert
public class Role extends AbstractEntity implements Serializable{

    @NotEmpty
    private String name;


    @ManyToMany(mappedBy = "roles", cascade={CascadeType.ALL})
    private List<User> users;


    public Role() {
        super();
    }


    public Role(String name) {
        super();
        this.name = name;
    }


    public String getName() {
        return name;
    }


    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }



    public List<User> getUsers() {
        return users;
    }


    public void setUsers(List<User> users) {
        this.users = users;
    }


    @Override
    public String toString() {
        return "Role [name=" + name + " | super = " + super.toString()+"]";
    }
}
