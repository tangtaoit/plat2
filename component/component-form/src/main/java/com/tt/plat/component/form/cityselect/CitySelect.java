package com.tt.plat.component.form.cityselect;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.string.AppendingStringBuffer;

import java.util.Collections;
import java.util.List;

/**
 * Created by tangtao on 2014/8/7.
 * Desc:
 */
public class CitySelect extends Panel{

    private ISelectedCallback provinceSelectedCallback;

    private ISelectedCallback citySelectedCallback;

    private ISelectedCallback areaSelectedCallback;

    private CitySelectDataProvider dataProvider;

    private DropDownChoice provinceChoice;

    private DropDownChoice cityChoice;

    private DropDownChoice areaChoice;


    //省份
    private CityModel province;

    //城市
    private CityModel city;

    //区域
    private CityModel area;

    public DropDownChoice getProvinceChoice() {
        return provinceChoice;
    }

    public DropDownChoice getCityChoice() {
        return cityChoice;
    }

    public DropDownChoice getAreaChoice() {
        return areaChoice;
    }

    public CityModel getProvince() {
        return province;
    }

    public void setProvince(CityModel province) {
        this.province = province;
    }

    public CityModel getCity() {
        return city;
    }

    public void setCity(CityModel city) {
        this.city = city;
    }

    public CityModel getArea() {
        return area;
    }

    public void setArea(CityModel area) {
        this.area = area;
    }

    public CitySelect(String id, final CitySelectDataProvider dataProvider) {
        super(id);
        this.dataProvider =dataProvider;


        //省份选择
        List<CityModel> provinces = dataProvider.getProvinces();
       provinceChoice = new DropDownChoice("province", new PropertyModel<String>(this, "province"), provinces, new ChoiceRenderer<CityModel>("value", "key")) {
            @Override
            protected CharSequence getDefaultChoice(String selectedValue) {
                return new AppendingStringBuffer("\n<option selected='selected' value=\"\">").append("请选择省份").append("</option>");
            }
        };
       // provinceChoice.setRequired(true);
        provinceChoice.setOutputMarkupId(true);
        provinceChoice.setNullValid(true);
        add(provinceChoice);

        //城市选择
        IModel<List<CityModel>> modelChoices = new AbstractReadOnlyModel<List<CityModel>>() {
            @Override
            public List<CityModel> getObject() {
                List<CityModel> areas = null;
                if (province != null) {
                    areas = dataProvider.getCities(province.getKey());
                }

                if (areas == null) {
                    areas = Collections.emptyList();
                }
                return areas;
            }

        };
       cityChoice = new DropDownChoice("city", new PropertyModel<String>(this, "city"), modelChoices, new ChoiceRenderer("value", "key")) {
            @Override
            protected CharSequence getDefaultChoice(String selectedValue) {

                return new AppendingStringBuffer("\n<option selected='selected' value=\"\">").append("请选择城市").append("</option>");
            }
        };
        cityChoice.setOutputMarkupId(true);
        //cityChoice.setRequired(true);
        cityChoice.setNullValid(true);
        add(cityChoice);


        //区选择
        IModel<List<CityModel>> modelareaChoices = new AbstractReadOnlyModel<List<CityModel>>() {
            @Override
            public List<CityModel> getObject() {
                List<CityModel> areas = null;
                if (city != null) {
                    areas = dataProvider.getAreas(city.getKey());
                }

                if (areas == null) {
                    areas = Collections.emptyList();
                }
                return areas;
            }

        };


        areaChoice = new DropDownChoice("area", new PropertyModel<String>(this, "area"), modelareaChoices, new ChoiceRenderer("value", "key")) {
            @Override
            protected CharSequence getDefaultChoice(String selectedValue) {

                return new AppendingStringBuffer("\n<option selected='selected' value=\"\">").append("请选择区").append("</option>");
            }
        };
        areaChoice.setOutputMarkupId(true);
        //areaChoice.setRequired(true);
        areaChoice.setNullValid(true);
        add(areaChoice);

        //省份值选择改变
        provinceChoice.add(new AjaxFormComponentUpdatingBehavior("onchange") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                target.add(cityChoice);

                if(provinceSelectedCallback!=null){
                    provinceSelectedCallback.selected(target);
                }

            }
        });

        //城市值选择改变
        cityChoice.add(new AjaxFormComponentUpdatingBehavior("onchange") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                target.add(areaChoice);

                if(citySelectedCallback!=null){
                    citySelectedCallback.selected(target);
                }
            }
        });

        //区域值选择改变
        areaChoice.add(new AjaxFormComponentUpdatingBehavior("onchange") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {

                if(areaSelectedCallback!=null){
                    areaSelectedCallback.selected(target);
                }
            }
        });

        provinceChoice.add(new AttributeModifier("class", "form-control"));
        cityChoice.add(new AttributeModifier("class", "form-control"));
        areaChoice.add(new AttributeModifier("class", "form-control"));


    }

    public void setProvinceSelectedCallback(ISelectedCallback provinceSelectedCallback) {
        this.provinceSelectedCallback = provinceSelectedCallback;
    }

    public void setCitySelectedCallback(ISelectedCallback citySelectedCallback) {
        this.citySelectedCallback = citySelectedCallback;
    }

    public void setAreaSelectedCallback(ISelectedCallback areaSelectedCallback) {
        this.areaSelectedCallback = areaSelectedCallback;
    }
}
