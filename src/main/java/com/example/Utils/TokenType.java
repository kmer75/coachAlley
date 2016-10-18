package com.example.Utils;

/**
 * Created by kevin on 18/10/2016.
 */
public enum TokenType {
    FORGET ("forget"),
    ACTIVE ("activer");

    private String type = "";

    TokenType(String type) {
        this.type = type;
    }

    public String toString(){
        return type;
    }

}
