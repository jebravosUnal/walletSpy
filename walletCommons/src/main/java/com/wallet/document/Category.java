package com.wallet.document;


import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by EBR3556 on 12/09/2017.
 */
@Document
public class Category extends LabelledDocument{

    @Override
    public int hashCode() {
        int result = getLabel().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Category)) return false;

        Category that = (Category) obj;

        return getLabel().equals(that.getLabel());
    }

    @Override
    public String toString() {
        return "{ "
                + (this.getLabel() != null ? " label : " + this.getLabel() + " \n" : "")
                + "}";
    }
}