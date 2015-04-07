package com.tt.plat.component.form.datatables;

/**
 * Created by tangtao on 2014/8/4.
 * Desc:
 */
public class PField implements IPField{

    private String label;

    private String name;

    private IpOpts[] ipOpts;

    private String def;

    private String type;

    public PField(String label, String name) {
        this.label = label;
        this.name = name;
    }

    public PField(String label, String name, String type) {
        this.label = label;
        this.name = name;
        this.type = type;
    }

    public PField(String label, String name, String def, String type) {
        this.label = label;
        this.name = name;
        this.def = def;
        this.type = type;
    }

    public PField(String label, String name, String type, IpOpts... ipOpts) {
        this.label = label;
        this.name = name;
        this.type = type;
        this.ipOpts = ipOpts;
    }

    public PField(String label, String name, IpOpts[] ipOpts, String def, String type) {
        this.label = label;
        this.name = name;
        this.ipOpts = ipOpts;
        this.def = def;
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IpOpts[] getIpOpts() {
        return ipOpts;
    }

    public void setIpOpts(IpOpts[] ipOpts) {
        this.ipOpts = ipOpts;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
