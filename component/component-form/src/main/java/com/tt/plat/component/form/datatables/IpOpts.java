package com.tt.plat.component.form.datatables;

/**
 * Created by tangtao on 2014/8/4.
 * Desc:
 */
public class IpOpts {

    private String label;

    private String value;

    public IpOpts(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public IpOpts(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
