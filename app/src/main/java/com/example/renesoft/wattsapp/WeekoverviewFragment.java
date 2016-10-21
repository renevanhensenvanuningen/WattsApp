package com.example.renesoft.wattsapp;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import org.jstrava.entities.activity.Activity;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeekoverviewFragment extends Fragment {

    Handler handler = new Handler();

    ListView lvactivitiesOverview;


    public WeekoverviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_weekoverview, container, false);
        lvactivitiesOverview = (ListView) v.findViewById(R.id.lvItemsOverview);

        return v;

    }

    @Override
    public void onResume()
    {
        super.onResume();
        new Thread(){
            public void run(){
                RemoteFetchStrava remoteFetch = new RemoteFetchStrava();

                final List<Activity> activities = remoteFetch.getActivities(getActivity());

                if(activities == null){
                    handler.post(new Runnable(){
                        public void run(){
                            Toast.makeText(getActivity(),
                                    "Not working",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    handler.post(new Runnable(){
                        public void run(){
                            renderWeather(activities);
                        }
                    });
                }
            }
        }.start();
    }

    private void renderWeather(List<Activity> act) {
        try
        {

            Object[]  arry = act.toArray();

            ArrayList<Activity> items = new ArrayList<Activity>();

            ActivityManager actManager  = new ActivityManager();
            for (int i = 0; i < arry.length; i++)
            {
                Activity a = (Activity) arry[i];
                actManager.addActivity(a);
            }
            ArrayList<WeekActivity> waitems = actManager.getWeekActivities();

            ActivityOverviewAdapter adapter = new ActivityOverviewAdapter(getContext(), waitems);

            //     ActivityAdapter<Activity> itemsAdapter = new ActivityAdapter<Activity> (getContext(), android.R.layout.simple_list_item_1, items);
            lvactivitiesOverview.setAdapter(adapter);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < act.size(); i++)
            {
                Activity a = act.get(i);
                String s  = a.getStart_date_local();
                sb.append(s);
            }
//

        } catch (Exception e) {
            Log.e("SimpleWeather", "One or more fields not found in the JSON data");
        }
    }


}
