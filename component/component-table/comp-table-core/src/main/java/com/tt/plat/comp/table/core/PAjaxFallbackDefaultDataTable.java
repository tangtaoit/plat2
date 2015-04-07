package com.tt.plat.comp.table.core;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackHeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NoRecordsToolbar;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.OddEvenItem;
import org.apache.wicket.model.IModel;

import java.util.List;

/**
 * Created by tangtao on 2015-01-06.
 */
public class PAjaxFallbackDefaultDataTable<T,S> extends DataTable<T,S> {

    private PAjaxNavigationToolbar ajaxNavigationToolbar;

    public PAjaxFallbackDefaultDataTable(String id, List<? extends IColumn<T, S>> columns, ISortableDataProvider<T, S> dataProvider, int rowsPerPage) {
        super(id, columns, dataProvider, rowsPerPage);
        this.setOutputMarkupId(true);
        this.setOutputMarkupPlaceholderTag(true);
        this.setVersioned(false);



        ajaxNavigationToolbar = new PAjaxNavigationToolbar(this);


       // this.addTopToolbar(ajaxNavigationToolbar);
        this.addTopToolbar(new AjaxFallbackHeadersToolbar(this, dataProvider));
        this.addBottomToolbar(ajaxNavigationToolbar);
        this.addBottomToolbar(new NoRecordsToolbar(this));

        WebMarkupContainer webMarkupContainer =this.getTopToolbars();
        webMarkupContainer.setVisible(true);

    }

//    public void addConditionComponent(Component component){
//
//        ajaxNavigationToolbar.addConditionComponent(component);
//    }

    protected Item<T> newRowItem(String id, int index, IModel<T> model) {
        return new OddEvenItem(id, index, model);
    }
}
