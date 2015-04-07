package com.tt.plat.comp.table.core;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.markup.repeater.OddEvenItem;
import org.apache.wicket.model.IModel;

/**
 * Created by tangtao on 2015-01-13.
 */
public class DataTableItem extends OddEvenItem {


    private DataTable table;
    /**
     * Constructor
     *
     * @param id    component id
     * @param index item index
     * @param model
     */
    public DataTableItem(String id, int index, IModel model, DataTable table) {
        super(id, index, model);
        this.table=table;
    }

    public DataTable getTable() {
        return table;
    }
}


