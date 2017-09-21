package com.wallet.entity;

/**
 * Created by EBR3556 on 21/09/2017.
 */
public class User extends NamedEntity {

        private String username;
        //TODO implement the password


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
