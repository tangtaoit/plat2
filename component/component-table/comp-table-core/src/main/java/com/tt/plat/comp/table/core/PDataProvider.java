package com.tt.plat.comp.table.core;

import com.tt.plat.comp.table.api.IDataSource;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Created by json on 2015-01-05.
 */
public class PDataProvider extends SortableDataProvider<Serializable,String> {

    private IDataSource dataSource;

    public PDataProvider(IDataSource dataSource){
        this.dataSource=dataSource;
    }
    @Override
    public Iterator iterator(long first, long count) {
        return dataSource.iterator(first, count);
    }

    @Override
    public long size() {

        return dataSource.totalSize();
    }

    @Override
    public IModel<Serializable> model(Serializable serializable) {
        return new Model(serializable);
    }

}
