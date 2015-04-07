package com.tt.plat.comp.charts;

import org.apache.wicket.model.IModel;

import java.util.List;

/**
 * Created by tangtao on 2014/9/13.
 * Desc:
 */
public class AjaxFlotPanel extends FlotPanel{

    public AjaxFlotPanel(String id, IModel<List<Series>> model) {
        super(id, model);
    }
}
