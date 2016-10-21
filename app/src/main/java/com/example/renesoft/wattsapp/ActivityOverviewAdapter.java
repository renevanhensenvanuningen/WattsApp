package com.example.renesoft.wattsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.jstrava.entities.activity.Activity;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by reneu on 7-9-2016.
 */
public class ActivityOverviewAdapter extends ArrayAdapter<WeekActivity> {

    public ActivityOverviewAdapter(Context context, ArrayList<WeekActivity> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
       WeekActivity activity = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_weekoverview, parent, false);
        }
        // Lookup view for data population
        TextView tvDatum = (TextView) convertView.findViewById(R.id.edWeekwo);
        TextView tvDistance = (TextView) convertView.findViewById(R.id.edAfstandwo);
        TextView tvAvg = (TextView)  convertView.findViewById(R.id.avghrwo);
        TextView tvSuffer = (TextView)  convertView.findViewById(R.id.edSufferwo);


        Date d = new Date();

        String strDate = new Integer(activity.weekNumber).toString();

        tvDatum.setText(strDate);
        double km = activity.km;

        tvDistance.setText(new Float(km).toString());

        int sufferS = activity.sufferScore;
        tvSuffer.setText(new Integer(sufferS).toString());
       // f = activity.getCalories();

        double avgHr = activity.avghr;
        tvAvg.setText(new Float(avgHr).toString());

        //tvCal.setText(new Integer(sufferS).toString());
        // Return the completed view to render on screen
        return convertView;
    }
}
