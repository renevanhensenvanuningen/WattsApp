package com.example.renesoft.wattsapp;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import org.jstrava.entities.athlete.Athlete;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {

    private Handler handler = new Handler();
    private TextView tvAthleteTextView;

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_blank, container, false);
        tvAthleteTextView = (TextView) v.findViewById(R.id.tvAthlete);
        return v;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        new Thread(){
            public void run(){
                RemoteFetchStrava remoteFetch = new RemoteFetchStrava();
                //  final JSONObject json = remoteFetch.getJSON(getActivity(), "Gouda");
             //   final JSONObject json  = remoteFetch.getJSON(getActivity(), "Gouda");
                final Athlete athlete = remoteFetch.getAthlete(getActivity());

                if(athlete == null){
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
                            renderWeather(athlete);
                        }
                    });
                }
            }
        }.start();
    }

    private void renderWeather(Athlete ath) {
        try
        {
            tvAthleteTextView.setText("");
            StringBuilder sb = new StringBuilder();
            sb.append(ath.toString());
            sb.append(ath.getCity());
            sb.append(ath.getBikes());
            sb.append(ath.getEmail());
            sb.append(ath.getUpdated_at());
            sb.append(ath.toString());
           // string s = sb.toString();
           // String a = new String;
            tvAthleteTextView.append(sb.toString());


        } catch (Exception e) {
            Log.e("SimpleWeather", "One or more fields not found in the JSON data");
        }
    }
}
