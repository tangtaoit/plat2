package com.tt.plat.component.form.cityselect;

import org.apache.wicket.model.IDetachable;

import java.util.List;
import java.util.Map;

/**
 * Created by tangtao on 2014/8/7.
 * Desc:
 */
public abstract  class CitySelectDataProvider implements IDetachable {


    /**
     * 获取省份
     * @return
     */
    public abstract List<CityModel> getProvinces();

    /**
     * 获取城市
     * @param provinceId 省份ID
     * @return
     */
    public abstract List<CityModel> getCities(String provinceId);

    /**
     * 获取区域
     * @param cityId 城市ID
     * @return
     */
    public abstract List<CityModel> getAreas(String cityId);

    /**
     * 获取街道
     * @param areaId 区域ID
     * @return
     */
    public abstract List<CityModel> getStreets(String areaId);





    @Override
    public void detach() {

    }
}
