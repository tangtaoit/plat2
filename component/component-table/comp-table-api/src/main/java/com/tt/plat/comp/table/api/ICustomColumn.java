package com.tt.plat.comp.table.api;

import org.apache.wicket.Component;
import org.apache.wicket.markup.repeater.Item;

/**
 * Created by json on 2015-01-05.
 */
public interface ICustomColumn<T> extends IPColumn{


    /**
     * 获取到自定义列的组件
     * @param componentId
     * @param t 当前列的实体对象
     * @return
     */
    public Component getComponent(String componentId,T t);

    public void setItem(Item item);

    public Item getItem();




}
