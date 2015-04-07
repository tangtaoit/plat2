package com.tt.plat.component.form;

import com.googlecode.wicket.jquery.core.Options;
import com.googlecode.wicket.jquery.ui.form.datepicker.DatePicker;
import com.googlecode.wicket.kendo.ui.form.datetime.TimePicker;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;

import java.util.Locale;

/**
 * Created by tao on 2015/3/14.
 */
public class PDatePicker extends DatePicker {


    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);



        tag.put("style","z-index:1050;position:relative;");
    }

    public PDatePicker(String id,IModel model) {



        super(id,model, new Options()
                .set("dateFormat", "\"yy-mm-dd\"")
                .set("monthNames", "[\"一月\",\"二月\",\"三月\",\"四月\",\"五月\",\"六月\",\"七月\",\"八月\",\"九月\",\"十月\",\"十一月\",\"十二月\"]")
                .set("monthNamesShort", "[\"一\", \"二\", \"三\", \"四\", \"五\", \"六\", \"七\", \"八\", \"九\", \"十\", \"十一\", \"十二\" ]")
                .set("dayNames", "[ \"星期日\",\"星期一\", \"星期二\", \"星期三\", \"星期四\", \"星期五\", \"星期六\"]")
                .set("dayNamesMin", "[\"日\",\"一\", \"二\", \"三\", \"四\", \"五\", \"六\"]").set("firstDay", 1));


       this.getLocale().setDefault(Locale.CHINA);
    }



}
