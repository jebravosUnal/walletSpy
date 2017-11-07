package com.wallet.document;


import org.springframework.data.mongodb.core.mapping.Document;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by EBR3556 on 12/09/2017.
 */
@Document
public class Category extends LabelledDocument{

    public final static Pattern NOT_DEFINED_PATTERN = Pattern.compile("Non défini");

    public boolean isDefined(){
        Matcher m = NOT_DEFINED_PATTERN.matcher(this.getLabel());
        return !m.matches();
    }

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