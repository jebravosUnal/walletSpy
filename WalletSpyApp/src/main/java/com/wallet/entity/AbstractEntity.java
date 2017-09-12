package com.wallet.entity;

import org.springframework.data.annotation.Id;

/**
 * Created by EBR3556 on 12/09/2017.
 */
public class AbstractEntity {

    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
