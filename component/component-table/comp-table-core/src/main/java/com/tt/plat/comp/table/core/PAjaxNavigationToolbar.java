package com.tt.plat.comp.table.core;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NavigatorLabel;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.model.AbstractReadOnlyModel;

/**
 * Created by tangtao on 2015-01-06.
 */
public class PAjaxNavigationToolbar extends AbstractToolbar {


    private WebMarkupContainer span;
    public PAjaxNavigationToolbar(final DataTable<?, ?> table) {
        super(table);
         span = new WebMarkupContainer("span");
        add(span);
        span.add(AttributeModifier.replace("colspan", new AbstractReadOnlyModel<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String getObject() {
                return String.valueOf(table.getColumns().size());
            }
        }));

        span.add(newPagingNavigator("navigator", table));
        span.add(newNavigatorLabel("navigatorLabel", table));


    }

//    /**
//     * 往table上添加条件组件
//     * @param component
//     */
//    public void addConditionComponent(Component component){
//
//        span.add(component);
//    }


    protected PagingNavigator newPagingNavigator(final String navigatorId, final DataTable<?, ?> table) {
        return new AjaxPagingNavigator(navigatorId, table) {
            private static final long serialVersionUID = 1L;


            @Override
            protected void onAjaxEvent(final AjaxRequestTarget target) {
                target.add(table);
            }
        };
    }


    /**
     * Factory method used to create the navigator label that will be used by the datatable
     *
     * @param navigatorId component id navigator label should be created with
     * @param table       dataview used by datatable
     * @return navigator label that will be used to navigate the data table
     */
    protected WebComponent newNavigatorLabel(final String navigatorId, final DataTable<?, ?> table) {
        return new NavigatorLabel(navigatorId, table);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onConfigure() {
        super.onConfigure();
       setVisible(getTable().getPageCount() > 1);
    }
}
