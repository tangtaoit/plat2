package com.tt.plat.comp.table.api.column;

import com.tt.plat.comp.table.api.ICustomColumn;
import org.apache.wicket.Component;
import org.apache.wicket.markup.repeater.Item;

/**
 * Created by json on 2015-01-05.
 */
public abstract class CustomColumn<T> implements ICustomColumn<T>{

    private String columnName;

    private Item item;

    public CustomColumn(String columnName) {
        this.columnName = columnName;
    }

    @Override
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Override
    public abstract Component getComponent(String componentId, T o);

    public  String  getCssClass(){

        return null;
    }

    public void setItem(Item item){
        this.item=item;
    }

    public Item getItem() {

        return item;

    }
}
