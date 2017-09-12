package com.wallet.domain.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * Created by EBR3556 on 12/09/2017.
 */
public abstract class AbstractEntity implements Serializable{

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
