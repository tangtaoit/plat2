package com.tt.plat.component.form.timeslot;

import com.tt.plat.component.form.PDatePicker;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import java.util.Date;


/**
 * Created by tao on 2015/3/14.
 */
public class TimeslotPanel extends Panel{



    public TimeslotPanel(String id,IModel model) {
        super(id,model);

        Timeslot timeslot = (Timeslot)getDefaultModel().getObject();

        /**
         * 开始时间
         */
        PDatePicker startDatePicker = new PDatePicker("startDate",new PropertyModel<Date>(timeslot,"startDate"));
        this.add(startDatePicker);

        TextField startTimeInputFd =  new TextField("startTime",new PropertyModel<String>(timeslot,"startTime"));
        this.add(startTimeInputFd);
        /**
         * 结束时间
         */
        PDatePicker endDatePicker = new PDatePicker("endDate",new PropertyModel<Date>(timeslot,"endDate"));
        this.add(endDatePicker);
        TextField endTimeInputFd =  new TextField("endTime",new PropertyModel<String>(timeslot,"endTime"));
        this.add(endTimeInputFd);
    }
}


