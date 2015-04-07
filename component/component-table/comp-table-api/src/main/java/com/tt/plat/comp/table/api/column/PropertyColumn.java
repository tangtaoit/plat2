package com.tt.plat.comp.table.api.column;

import com.tt.plat.comp.table.api.IPropertyColumn;

/**
 * Created by json on 2015-01-05.
 */
public class PropertyColumn implements IPropertyColumn{

    private String field;

    private String columnName;


    public PropertyColumn(String columnName, String field) {
        this.field = field;
        this.columnName = columnName;
    }

    @Override
    public String getField() {
        return field;
    }

    @Override
    public String getColumnName() {
        return columnName;
    }

    @Override
    public String getCssClass() {
        return null;
    }
}
