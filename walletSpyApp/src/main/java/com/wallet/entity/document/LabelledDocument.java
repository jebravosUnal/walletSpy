package com.wallet.entity.document;

/**
 * Created by EBR3556 on 12/09/2017.
 */
public class LabelledDocument extends AbstractDocument {

    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}