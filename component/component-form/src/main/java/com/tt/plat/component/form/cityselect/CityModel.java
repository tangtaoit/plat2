package com.tt.plat.component.form.cityselect;

import org.apache.wicket.util.io.IClusterable;

/**
 * Created by tangtao on 2014/8/7.
 * Desc:
 */
public class CityModel implements IClusterable{

    private String key;

    private String value;

    public CityModel(){

    }

    public CityModel(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
