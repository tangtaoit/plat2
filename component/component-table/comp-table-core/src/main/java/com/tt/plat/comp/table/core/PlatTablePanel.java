package com.tt.plat.comp.table.core;

import com.tt.plat.comp.table.api.*;
import com.tt.plat.core.utils.JNDIHelper;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by json on 2015-01-05.
 */
public abstract class PlatTablePanel extends Panel{

    public static String CONDITION_ID="condition";

    private String title;

    private PAjaxFallbackDefaultDataTable table;

    /**
     * 头部容器
     */
    private   WebMarkupContainer headContainer;


    public PlatTablePanel(String id) {
        super(id);

        //获取到table数据源
        ITableSource tableSource = getTableSource();

        //获取到table的列数据
        List<IColumn> columnList1 = getColumnList(tableSource.getColumnList());




        table=new PAjaxFallbackDefaultDataTable("table", columnList1,
                new PDataProvider(tableSource.getDataSource()), tableSource.getPageSize()){


            @Override
            protected Item<IColumn> newCellItem(String id, int index, IModel model) {
                return new DataTableItem(id, index, model,this);
            }
        };
        table.setRenderBodyOnly(false);

        add(table);

        tableSource.setTableRefreshCallback(new ITableRefreshCallback() {
            @Override
            public void tablerefreshCallback(AjaxRequestTarget target) {
                target.add(table);
            }
        });

        /**
         * 头部容器
         */
         headContainer = new WebMarkupContainer("headContainer");
        add(headContainer);

        /**
         *条件组件
         */
        Component conditionComponent = tableSource.getCondition(CONDITION_ID);
        if(conditionComponent!=null){
            headContainer.add(conditionComponent);
        }else{
            headContainer.add(new ConditionPanel(CONDITION_ID));
        }

        //添加条件组件
       // table.addConditionComponent(tableSource.getCondition(PAjaxNavigationToolbar.CONDITION_ID));


        Label titleLabel = new Label("title",new PropertyModel<>(this,"title"));
        add(titleLabel);
    }


    public String getConditionId(){

        return CONDITION_ID;
    }

    /**
     * 往table上添加条件组件
     * @param component
     */
    public void setConditionComponent(Component component){

        headContainer.removeAll();
        headContainer.add(component);


    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取到wicket table的列模型集合
     * @param pcolumnList
     * @return
     */
    private List<IColumn> getColumnList(List<IPColumn> pcolumnList){

        List<IColumn> columnList = new ArrayList<>();
        if(pcolumnList!=null){
            for(IPColumn column:pcolumnList){

                columnList.add(getColumn(column));
            }
        }

        return columnList;
    }

    /**
     * 获取到wicket table的列模型
     * @param column
     * @return
     */
    private IColumn getColumn(IPColumn column){
        if(column instanceof IPropertyColumn){ //属性列

            IPropertyColumn propertyColumn = (IPropertyColumn)column;
            return  new PropertyColumn(new Model<String>(propertyColumn.getColumnName()),propertyColumn.getField());

        }else if(column instanceof ICustomColumn){ //自定义列

            final ICustomColumn customColumn = (ICustomColumn)column;

            return new AbstractColumn(new Model<String>(customColumn.getColumnName())) {
                @Override
                public void populateItem(Item item, String compentId, IModel iModel) {
                    customColumn.setItem(item);
                   item.add(customColumn.getComponent(compentId,iModel.getObject()));
                }

                @Override
                public String getCssClass() {
                    return customColumn.getCssClass();
                }
            };
        }

        return null;

    }


//    /**
//     * 获取到 tablesource的服务
//     * @param filterName 过滤服务的条件
//     * @return
//     */
//    private ITableSource getTableSource(String filterName){
//
//        try {
//         ITableSource tableSource=    JNDIHelper.getJNDIServiceForName(ITableSource.class.getName() + "/(" + filterName + ")");
//            tableSource.getDataSource().refresh();
//            return tableSource;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }


    public PAjaxFallbackDefaultDataTable getTable() {
        return table;
    }

    /**
     * 获取数据源
     * @return
     */
    public abstract ITableSource getTableSource();
}
