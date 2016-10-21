package com.example.renesoft.wattsapp;

import org.jstrava.entities.activity.Activity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by reneu on 7-9-2016.
 */
public class ActivityManager {

    private ArrayList<WeekActivity> weekActivs = new ArrayList<WeekActivity>();

    private WeekActivity findWeekActivity(int week)
    {
        for (int i =0 ; i < weekActivs.size(); i++)
        {
            if (weekActivs.get(i).weekNumber == week)
            {
                return weekActivs.get(i);
            }
        }
        WeekActivity wa = new WeekActivity();
        wa.weekNumber = week;
        weekActivs.add(wa);
        return  wa;
    }

    public void addActivity(Activity a)
    {
        String sDate = a.getStart_date();
        sDate = sDate.substring(0,10);
        Date d = Date.valueOf(sDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        WeekActivity wa = findWeekActivity(week);
        wa.sufferScore += a.getSuffer_score();
        wa.km += a.getDistance() / 1000;
        wa.avghr = (int) ((wa.avghr + a.getAverage_heartrate() ) / 2);
    }

    public ArrayList<WeekActivity> getWeekActivities()
    {
       return weekActivs;
    }

}
