package com.tt.plat.component.form.timeslot;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tao on 2015/3/14.
 */
public class Timeslot {

    private DateFormat dateFormat1 =new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private DateFormat dateFormat2 =new SimpleDateFormat("yyyy-MM-dd");

    private Date startDate;

    private Date endDate;

    private String startTime;

    private String endTime;

    /**
     * 获取完整的开始时间
     * @return
     */
    public Date getFullStartDate(){

       String sstartDate = dateFormat2.format(getStartDate());
        String sfullStartDate = sstartDate+" "+startTime;

        try {
          return   dateFormat1.parse(sfullStartDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取完整的结束时间
     * @return
     */
    public Date getFullEndDate(){

        String sendDate = dateFormat2.format(getEndDate());
        String sfullEndDate = sendDate+" "+endTime;

        try {
            return   dateFormat1.parse(sfullEndDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
