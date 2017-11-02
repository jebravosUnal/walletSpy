package com.wallet.entity;

import com.google.common.collect.Lists;
import com.wallet.document.Account;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by EBR3556 on 21/09/2017.
 */
public class User extends NamedEntity {

    private String username;
    //TODO implement the password

    private List<Account> accounts;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Account> getAccounts() {
        if(accounts == null){
            return newArrayList();
        }
        return Lists.newArrayList(accounts);
    }

    private void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
