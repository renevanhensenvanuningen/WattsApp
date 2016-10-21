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
 * Created by android3 on 9/3/16.
 */
public class ActivityAdapter extends ArrayAdapter<Activity> {
        public ActivityAdapter(Context context, ArrayList<Activity> users) {
            super(context, 0, users);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Activity activity = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_activity, parent, false);
            }
            // Lookup view for data population
            TextView tvDatum = (TextView) convertView.findViewById(R.id.edDatum);
            TextView tvDistance = (TextView) convertView.findViewById(R.id.edAfstand);
            TextView tvCal = (TextView) convertView.findViewById(R.id.edCal);
            TextView tvAvg = (TextView)  convertView.findViewById(R.id.avghr);

            // Populate the data into the template view using the data object
            Date d = new Date();

            String strDate = activity.getStart_date();
            strDate = strDate.substring(2,10);
            tvDatum.setText(strDate);
            Float f = activity.getDistance();

            tvDistance.setText(String.format("%.3f",f/1000));
            int sufferS = activity.getSuffer_score();
            f = activity.getCalories();

            double avgHr = activity.getAverage_heartrate();
            tvAvg.setText(new Float(avgHr).toString());

            tvCal.setText(new Integer(sufferS).toString());
            // Return the completed view to render on screen
            return convertView;
        }
    }

