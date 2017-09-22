package com.wallet.document;


import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by EBR3556 on 12/09/2017.
 */
@Document
public class Category extends LabelledDocument{

    @Override
    public String toString() {
        return "{ "
                + (this.getLabel() != null ? " label : " + this.getLabel() + " \n" : "")
                + "}";
    }
}