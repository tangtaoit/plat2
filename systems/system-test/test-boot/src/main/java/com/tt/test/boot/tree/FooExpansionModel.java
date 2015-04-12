package com.tt.test.boot.tree;

import org.apache.wicket.model.AbstractReadOnlyModel;

import java.util.Set;

/**
 * Created by tao on 2015/4/12.
 */
public class FooExpansionModel extends AbstractReadOnlyModel<Set<Foo>> {


    @Override
    public Set<Foo> getObject() {
        return FooExpansion.get();
    }
}
