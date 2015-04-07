package com.tt.plat.comp.charts;

/**
 * Created by tangtao on 2014/9/13.
 * Desc:
 */
public class TooltipOpts {
    private String content;

    public TooltipOpts(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "{content:\""+getContent()+"\"}";
    }
}
