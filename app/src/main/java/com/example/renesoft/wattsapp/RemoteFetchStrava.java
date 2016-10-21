package com.example.renesoft.wattsapp;

import android.content.Context;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import org.jstrava.*;
import org.jstrava.connector.JStravaV3;
import org.jstrava.entities.activity.Activity;
import org.jstrava.entities.athlete.Athlete;

public class RemoteFetchStrava {

    private static final String STRAVA_API = "https://www.strava.com/api/v3/athlete";
    private static final String OPEN_WEATHER_API = "http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric";

    public final JSONObject getJSON(Context context, String city) {
        try {
            Test(context.getString(R.string.strava_app_id));
            URL url = new URL(STRAVA_API);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            //  connection.addRequestProperty("x-api-key", context.getString(R.string.open_weather_maps_app_id));
//            connection.addRequestProperty("access_token", context.getString(R.string.strava_app_id));
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer json = new StringBuffer(1024);
            String tmp = "";
            while ((tmp = reader.readLine()) != null) {
                json.append(tmp).append("\n");
                //    reader.close();
            }
            reader.close();
            JSONObject data = new JSONObject(json.toString());
            // This value will be 404 if the request was not
            // successful
            reader.close();
            return data;

        } catch (Exception ex) {
            String s = "{fout}";
            try {
                JSONObject j = new JSONObject(s.toString());
                return j;
            } catch (Exception e) {
                return null;
            }
        }
    }

    public List<Activity> getActivities(Context c)
    {
        JStravaV3 jstrava = new JStravaV3(c.getString(R.string.strava_app_id));
        return jstrava.getCurrentAthleteActivities();

    }

    public Athlete getAthlete(Context c)
    {
        JStravaV3 jstrava = new JStravaV3(c.getString(R.string.strava_app_id));
        Athlete a = jstrava.getCurrentAthlete();
        return a;
    }

    public void Test(String key)
    {
   // String header = OAuthClient.prepareURLWithOAuthSignature(url1);

    HttpsURLConnection con = null;

    try {
        URL obj = new URL(STRAVA_API);
        con = (HttpsURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("access_token=", key);

        int responseCode = con.getResponseCode();

        System.out.println("Response Code = " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        con.disconnect();
        //print result
        System.out.println("Response = " + response.toString());


    } catch (MalformedURLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (con != null) con.disconnect();
    }
    }

}








